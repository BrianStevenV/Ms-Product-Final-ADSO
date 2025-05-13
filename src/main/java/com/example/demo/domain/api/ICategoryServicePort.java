package com.example.demo.domain.api;

import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;

import java.util.List;

public interface ICategoryServicePort {
    List<Category> getAllCategories();
    List<CategoryAttribute> getAllCategoryAttributesByCategoryId(long categoryId);
}
