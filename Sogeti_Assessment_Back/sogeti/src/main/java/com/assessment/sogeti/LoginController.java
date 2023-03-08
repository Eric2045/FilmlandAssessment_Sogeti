package com.assessment.sogeti;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


/*
class named LoginController, annotated with @RestController and @RequestMapping("/login").
It contains methods that handle HTTP requests related to login and subscription to categories.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;
    private final SubscriptionServ subscriptionService;
    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final AvailableCategories availableCategories;
    private final SubscriptionRepository subscriptionRepository;

    public LoginController(UserService userService, SubscriptionService subscriptionService, UserRepository userRepository, CategoryService categoryService, AvailableCategories availableCategories, SubscriptionRepository subscriptionRepository) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.availableCategories = availableCategories;
        this.subscriptionRepository = subscriptionRepository;
    }

    /*
    This method returns a ResponseEntity that contains a message indicating whether the login was successful or not.
    If the login was successful, an authentication token is generated and returned in the response headers.
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Invalid Username"));
        } else if (user.checkPassword(password)) {

            String authToken = UUID.randomUUID().toString();
            user.setAuthToken(authToken);
            userRepository.save(user);

            return ResponseEntity.ok()
                    .header("Authorization", authToken)
                    .body(Map.of("status", "Login successful", "message", "Welcome back, " + user.getUsername() + "!"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("status", "Login failed", "message", "Invalid password."));
        }
    }

    /*
    This method takes in an Authentication object and returns a ResponseEntity that contains a list of available and
    subscribed categories for the authenticated user.
     */
    @GetMapping("/categories")
    public ResponseEntity<Map<String, List<CategoryTransfer>>> getCategories(Authentication authentication) {
        // Get the authenticated user

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        User user = userService.findByEmail(authentication.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Get the available categories
        List<Category> categories = categoryService.getAvailableCategories();

        // Get the subscribed categories of the authenticated subscriber
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(user);
        List<Category> subscribedCategories = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            subscribedCategories.add(subscription.getCategory());
        }

        // Create the response object
        Map<String, List<CategoryTransfer>> response = new HashMap<>();
        response.put("availableCategories", categories.stream()
                .map(CategoryTransfer::new)
                .collect(Collectors.toList()));
        response.put("subscribedCategories", subscribedCategories.stream()
                .map(c -> new CategoryTransfer(c, user))
                .collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }

    /*
     takes in a Map containing the email and the name of the category to subscribe to.
     If the email is not found in the database, a response is returned indicating that the login has failed.
     If the category is not found, a response is returned indicating that the subscription has failed. If the user is already subscribed to the category, a response is returned indicating that the subscription has failed. Otherwise, a new subscription is created for the user and category, with an end date that is one month from the current date. A response is returned indicating that the subscription was successful.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, String>> subscribeToCategory(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String categoryName = request.get("availableCategory");

        User user = userRepository.findUsername(email);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "Login failed");
            response.put("message", "User with email " + email + " not found");
            return ResponseEntity.badRequest().body(response);
        }

        Category category = availableCategories.getCategoryByName(categoryName);
        if (category == null) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "Subscription failed");
            response.put("message", "Category " + categoryName + " not found");
            return ResponseEntity.badRequest().body(response);
        }

        Subscription existingSubscription = user.getSubscriptionByCategory(category);
        if (existingSubscription != null) {
            Map<String, String> response = new HashMap<>();
            response.put("status", "Subscription failed");
            response.put("message", "User already subscribed to category " + categoryName);
            return ResponseEntity.badRequest().body(response);
        }

        // Calculate subscription end date (one month from now)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date subscriptionEndDate = calendar.getTime();

        // Create new subscription for user and category
        Subscription newSubscription = new Subscription(user, category,subscriptionEndDate);
        subscriptionRepository.save(newSubscription);

        Map<String, String> response = new HashMap<>();
        response.put("status", "Subscription successful");
        response.put("message", "Subscription to category " + categoryName + " successful");
        return ResponseEntity.ok().body(response);
    }

}
