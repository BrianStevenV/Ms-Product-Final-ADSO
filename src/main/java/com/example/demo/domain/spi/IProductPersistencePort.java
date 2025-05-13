package com.example.demo.domain.spi;

import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;

import java.util.Optional;

public interface IProductPersistencePort {
    void saveProduct(Product product);
    Optional<Product> findById(long productId);
    CustomPage<Product> getProductFeed();
    CustomPage<Product> getAllProductsByProviderId(Long userProviderId, int page, int size);
}
