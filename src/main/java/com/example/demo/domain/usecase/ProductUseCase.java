package com.example.demo.domain.usecase;

import com.example.demo.domain.api.IProductServicePort;
import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.spi.IProductPersistencePort;

public class ProductUseCase implements IProductServicePort {
    private final IProductPersistencePort productPersistencePort;

    public ProductUseCase(IProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public CustomPage<Product> getProductFeed() {
        return productPersistencePort.getProductFeed();
    }
}
