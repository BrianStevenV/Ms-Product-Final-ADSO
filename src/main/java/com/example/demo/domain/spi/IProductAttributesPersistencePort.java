package com.example.demo.domain.spi;

import com.example.demo.domain.model.value.object.ProductAttribute;

import java.util.List;
import java.util.Optional;

public interface IProductAttributesPersistencePort {
    void save(ProductAttribute productAttribute);
    Optional<ProductAttribute> findById(long productAttributeId);
    List<ProductAttribute> getAllProductAttributes();
}
