package com.example.demo.domain.api;

import com.example.demo.domain.model.CustomPage;
import com.example.demo.domain.model.Product;

public interface IProductServicePort {
    CustomPage<Product> getProductFeed();
}
