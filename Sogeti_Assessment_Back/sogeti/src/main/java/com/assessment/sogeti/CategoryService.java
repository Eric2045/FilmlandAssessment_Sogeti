package com.assessment.sogeti;

import java.util.List;

public interface CategoryService {
    List<Category> getAvailableCategories();
    List<Category> getSubscribedCategories(String username);
}
