package com.example.demo.domain.api;

import com.example.demo.adapters.driving.http.handler.commands.CreateProductAttributeCommandRequest;

public interface IProductAttributeServicePort {
    void createProductAttribute(CreateProductAttributeCommandRequest productAttributeCommandRequest);
}
