package com.example.demo.domain.api;

import com.example.demo.adapters.driving.http.handler.commands.ProductAttributeCommandRequest;
import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;

import java.util.List;

public interface IProductServicePort {
    CustomPage<Product> getProductFeed();
    void createProduct(Product product, long categoryId, List<ProductAttributeCommandRequest> attributes);
    void disableProduct(Long productId);
    CustomPage<Product> getAllStockByProviderId();
    void addProductQuantity(Long productId, Integer quantity);
    Product getProductByIdAndValidationUserProviderId(Long productId);
}
