package com.assessment.sogeti;


/*
This is a class is used for transferring information about a Category object.
There are two constructors for this class, both of which take a Category object as a parameter.
The first constructor takes only the Category object and sets the name, availableContent,
and price instance variables to the corresponding values from the Category object.

The second constructor also takes a User object as a parameter and does the same thing as the first constructor,
but it also checks if the User object has a subscription to the Category object passed in. If a subscription exists,
it sets the remainingContent instance variable to the value returned from the getRemainingContent() method of the Category object and
sets the startDate instance variable to the string representation of the LocalDate returned from the getStartDate() method of the Category object.
 */
public class CategoryTransfer {
    private final String name;
    private final int availableContent;
    private final double price;

    public CategoryTransfer(Category category) {
        this.name = category.getName();
        this.availableContent = category.getAvailableContent();
        this.price = category.getPrice();
    }

    public CategoryTransfer(Category category, User user) {
        this.name = category.getName();
        this.availableContent = category.getAvailableContent();
        this.price = category.getPrice();

        Subscription subscription = user.getSubscriptionByCategory(category);
        if (subscription != null) {
            Integer remainingContent = category.getRemainingContent();
            String startDate = category.getStartDate().toString();
        }
    }
}
