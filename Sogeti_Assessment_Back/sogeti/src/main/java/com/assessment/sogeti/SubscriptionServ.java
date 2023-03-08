package com.assessment.sogeti;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionServ {

    List<Subscription> getSubscriptionsByUser(User user);
    Subscription subscribe(User user, Category category, LocalDate startDate);
    void unsubscribe(User user, Category category);
    //int getRemainingContent(Subscription subscription);
    boolean hasRemainingContent(Subscription subscription);
    void decrementRemainingContent(Subscription subscription);
    Subscription findByUserAndCategory(User user, Category category);
}
