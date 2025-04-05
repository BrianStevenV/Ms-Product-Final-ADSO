package com.example.demo.domain.spi;

import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;

public interface IProductPersistencePort {
    CustomPage<Product> getProductFeed();
}
