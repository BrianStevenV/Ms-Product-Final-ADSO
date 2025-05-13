package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.CategoryAttributesEntityInt;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.ICategoryAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.ICategoryAttributeIntRepository;
import com.example.demo.domain.model.value.object.CategoryAttribute;
import com.example.demo.domain.spi.ICategoryAttributePersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryAttributesPostgresqlAdapter implements ICategoryAttributePersistencePort {
    private final ICategoryAttributeIntRepository categoryAttributeRepository;
    private final ICategoryAttributeEntityMapper categoryAttributeEntityMapper;

    @Override
    public void save(CategoryAttribute categoryAttribute) {
        categoryAttributeRepository.save(
                categoryAttributeEntityMapper.toCategoryAttributeEntityInt(categoryAttribute)
        );
    }

    @Override
    public Optional<CategoryAttribute> findById(long categoryId) {
        return categoryAttributeRepository.findById(categoryId)
                .map(categoryAttributeEntityMapper::toCategoryAttribute);
    }


    @Override
    public List<CategoryAttribute> findByAllByCategoryId(long categoryId) {
        return categoryAttributeRepository.findByCategory_Id(categoryId)
                .stream()
                .map(categoryAttributeEntityMapper::toCategoryAttribute)
                .toList();
    }
}
