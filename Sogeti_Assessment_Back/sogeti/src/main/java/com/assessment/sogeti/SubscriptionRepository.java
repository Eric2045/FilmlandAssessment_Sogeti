package com.assessment.sogeti;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);

    Subscription findByUserAndCategory(User user, Category category);
}
