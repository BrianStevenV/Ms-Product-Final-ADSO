package com.example.demo.adapters.driven.jpa.postgresql.adapters;

import com.example.demo.adapters.driven.jpa.postgresql.entities.ProductAttributesEntity;
import com.example.demo.adapters.driven.jpa.postgresql.mappers.IProductAttributeEntityMapper;
import com.example.demo.adapters.driven.jpa.postgresql.repositories.IProductAttributeRepository;
import com.example.demo.domain.model.value.object.ProductAttribute;
import com.example.demo.domain.spi.IProductAttributesPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductAttributesPostgresqlAdapter implements IProductAttributesPersistencePort{

    private final IProductAttributeRepository productAttributeRepository;
    private final IProductAttributeEntityMapper productAttributeEntityMapper;

    @Override
    public void save(ProductAttribute productAttribute) {
        productAttributeRepository.save(
                productAttributeEntityMapper.toProductAttributeEntity(productAttribute)
        );
    }

    @Override
    public Optional<ProductAttribute> findById(long productAttributeId) {
        return productAttributeRepository.findById(productAttributeId)
                .map(productAttributeEntityMapper::toProductAttribute);
    }

    @Override
    public List<ProductAttribute> getAllProductAttributes() {
        List<ProductAttributesEntity> productAttributesEntities = productAttributeRepository.findAll();
        return List.of();
    }
}
