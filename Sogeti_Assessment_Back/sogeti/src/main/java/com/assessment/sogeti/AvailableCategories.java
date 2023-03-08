package com.assessment.sogeti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
This class represents a collection of available categories.
It contains methods to add, get, and retrieve available categories.
 */
public class AvailableCategories implements CategoryService {

    private final Map<String, Category> categories = new HashMap<>();

    public AvailableCategories() {
        // Initialize categories here
        Category c1 = new Category("Hollywood Movies", 10, 9.99);
        Category c2 = new Category("Bollywood Movies", 5, 7.99);
        Category c3 = new Category("Dutch Films", 20, 6.99);
        Category c4 = new Category("Korean Dramas", 15, 8.99);

        categories.put(c1.getName(), c1);
        categories.put(c2.getName(), c2);
        categories.put(c3.getName(), c3);
        categories.put(c4.getName(), c4);
    }

    // Returns a list of all available categories.
    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }

    // Returns a category with the specified name.
    public Category getCategoryByName(String name) {
        return categories.get(name);
    }

    // Adds a category to the available categories.
    public void addCategory(Category category) {
        categories.put(category.getName(), category);
    }


    @Override
    public List<Category> getAvailableCategories() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public List<Category> getSubscribedCategories(String username) {
        return new ArrayList<>();
    }
}
