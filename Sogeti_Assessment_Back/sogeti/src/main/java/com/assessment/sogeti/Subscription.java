package com.assessment.sogeti;

import java.time.LocalDate;
import java.util.Date;

/*
This class represents a subscription that a user has to a category. It has instance variables such as id, category, user, remainingContent,
price, startDate, endDate, dutchSeries, dutchFilms, internationalFilms, and monthlyLimit.
The id represents a unique identifier for each subscription, the category is the category of content that the user subscribes to,
the user is the user that subscribed to the category, remainingContent represents how much content is left for the user to watch,
price represents the cost of the subscription, startDate represents the date the subscription was started,
and endDate represents the date the subscription ends.
 */
public class Subscription {

    private Long id;
    private Category category;
    private User user;
    private int remainingContent;
    private double price;
    private LocalDate startDate;
    private Date endDate;


    private boolean dutchSeries;
    private boolean dutchFilms;
    private boolean internationalFilms;
    private int monthlyLimit;

    public Subscription(Category category, User user, int remainingContent, LocalDate startDate) {
        this.id = null;
        this.category = category;
        this.user = user;
        this.remainingContent = remainingContent;
        this.price = category.getPrice();
        this.startDate = startDate;
    }

    public Subscription(User user, Category category, Date endDate) {
        this.category = category;
        this.user = user;
        this.endDate = endDate;
    }

    public Subscription(User user) {
        this.user = user;
    }

    public void setPrice(double price) {
        this.price = category.getPrice();;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public int getRemainingContent() {
        return remainingContent;
    }

    public void setRemainingContent(int remainingContent) {
        this.remainingContent = remainingContent;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public boolean isDutchSeries() {
        return dutchSeries;
    }

    public void setDutchSeries(boolean dutchSeries) {
        this.dutchSeries = dutchSeries;
    }

    public boolean isDutchFilms() {
        return dutchFilms;
    }

    public void setDutchFilms(boolean dutchFilms) {
        this.dutchFilms = dutchFilms;
    }

    public boolean isInternationalFilms() {
        return internationalFilms;
    }

    public void setInternationalFilms(boolean internationalFilms) {
        this.internationalFilms = internationalFilms;
    }

    public int getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(int monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Category getSubCategory() {
        return null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getEndDate() {
        return endDate;
    }
}
