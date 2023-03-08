package com.assessment.sogeti;

import java.time.LocalDate;


/*
This is a class definition for a Category object,The Category class has several properties,
including the name of the category, the number of available content items, the remaining content items,
the price of the category, and the start date of the category.
 */
public class Category {

    private String name;
    private int availableContent;
    private int remainingContent;
    private double price;
    private LocalDate startDate;

    public Category(String name, int availableContent, double price) {
        this.name = name;
        this.availableContent = availableContent;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableContent() {
        return availableContent;
    }

    public void setAvailableContent(int availableContent) {
        this.availableContent = availableContent;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}


