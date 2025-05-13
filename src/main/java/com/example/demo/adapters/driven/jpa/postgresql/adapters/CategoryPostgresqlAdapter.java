package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryEntity;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICategoryEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICategoryRepository;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryPostgresqlAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id)
                .map(categoryEntityMapper::toCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntityMapper::toCategory)
                .toList();
    }
}
