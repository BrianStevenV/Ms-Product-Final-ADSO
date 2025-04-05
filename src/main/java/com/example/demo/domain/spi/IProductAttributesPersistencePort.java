package com.example.demo.domain.spi;

import com.example.demo.domain.model.value.object.ProductAttribute;

import java.util.List;

public interface IProductAttributesPersistencePort {
    List<ProductAttribute> getAllProductAttributes();
}
