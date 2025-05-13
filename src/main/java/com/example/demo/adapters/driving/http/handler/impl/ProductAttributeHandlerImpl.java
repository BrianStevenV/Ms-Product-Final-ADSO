package com.example.demo.adapters.driving.http.handler.impl;

import com.example.demo.adapters.driving.http.dto.request.CreateProductAttributeRequestDto;
import com.example.demo.adapters.driving.http.handler.IProductAttributeHandler;
import com.example.demo.adapters.driving.http.mappers.IProductAttributeRequestMapper;
import com.example.demo.domain.api.IProductAttributeServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAttributeHandlerImpl implements IProductAttributeHandler {
    private final IProductAttributeServicePort productAttributeServicePort;
    private final IProductAttributeRequestMapper productAttributeRequestMapper;

    @Override
    public void createProductAttribute(CreateProductAttributeRequestDto productAttributesRequestDto) {
        productAttributeServicePort.createProductAttribute(
                productAttributeRequestMapper.toCreateProductAttributeCommandRequest(productAttributesRequestDto)
        );
    }
}
