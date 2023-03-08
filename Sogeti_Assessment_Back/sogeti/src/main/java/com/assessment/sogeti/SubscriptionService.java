package com.assessment.sogeti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/*
The SubscriptionService class is a service that provides methods to interact with the Subscription and User entities.
It implements the SubscriptionServ interface. It is annotated with @Service which makes it a candidate for autowiring into other components.
The SubscriptionService has two dependencies: SubscriptionRepository and UserRepository, which are autowired via constructor injection.
This class provides methods to get subscriptions by user, subscribe to a new Subscription, unsubscribe from a Subscription,
check if a Subscription has remaining content, decrement the remaining content of a Subscription, find a Subscription by user and category,
get all subscriptions for a User, and subscribe to a Subscription based on specific criteria.
 */
@Service
public class SubscriptionService implements SubscriptionServ{

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                                   UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(User user) {
        return subscriptionRepository.findByUser(user);
    }

    @Override
    public Subscription subscribe(User user, Category category, LocalDate startDate) {
        Subscription subscription = new Subscription(user);
        subscription.setUser(user);
        subscription.setCategory(category);
        subscription.setRemainingContent(category.getAvailableContent());
        subscription.setPrice(category.getPrice());
        subscription.setStartDate(LocalDate.now());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void unsubscribe(User user, Category category) {
        Subscription subscription = subscriptionRepository.findByUserAndCategory(user, category);
        subscriptionRepository.delete(subscription);
    }

    @Override
    public boolean hasRemainingContent(Subscription subscription) {
        return subscription.getRemainingContent() > 0;
    }

    @Override
    public void decrementRemainingContent(Subscription subscription) {
        subscription.setRemainingContent(subscription.getRemainingContent() - 1);
        subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription findByUserAndCategory(User user, Category category) {
        return subscriptionRepository.findByUserAndCategory(user, category);
    }

    public Subscription subscribe(User user, boolean dutchSeries, boolean dutchFilms, boolean internationalFilms, int monthlyLimit) {
        Subscription subscription = new Subscription(user);
        subscription.setUser(user);
        subscription.setDutchSeries(dutchSeries);
        subscription.setDutchFilms(dutchFilms);
        subscription.setInternationalFilms(internationalFilms);
        subscription.setMonthlyLimit(monthlyLimit);

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getSubscriptions(User user) {
        return subscriptionRepository.findByUser(user);
    }
}
