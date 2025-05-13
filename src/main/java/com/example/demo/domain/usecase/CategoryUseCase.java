package com.example.demo.domain.usecase;

import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import com.example.demo.domain.spi.ICategoryPersistencePort;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryAttributePersistencePort categoryAttributePersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort,
                           ICategoryAttributePersistencePort categoryAttributePersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryAttributePersistencePort = categoryAttributePersistencePort;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.findAll()
                .stream()
                .filter(Category::getIsActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryAttribute> getAllCategoryAttributesByCategoryId(long categoryId) {
        return categoryAttributePersistencePort.findByAllByCategoryId(categoryId);
    }

}

