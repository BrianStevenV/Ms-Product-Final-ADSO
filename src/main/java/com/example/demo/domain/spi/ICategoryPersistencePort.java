package com.example.demo.domain.spi;

import com.example.demo.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    Optional<Category> findById(long id);
    List<Category> findAll();
}
