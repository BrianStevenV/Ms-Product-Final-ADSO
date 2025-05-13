package com.example.demo.domain.spi;

import com.example.demo.domain.model.value.object.CategoryAttribute;

import java.util.List;
import java.util.Optional;

public interface ICategoryAttributePersistencePort {
    void save(CategoryAttribute categoryAttribute);
    Optional<CategoryAttribute> findById(long categoryId);
    List<CategoryAttribute> findByAllByCategoryId(long categoryId);
}
