package com.example.demo.adapters.driving.http.controller;

import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;
import com.example.demo.adapters.driving.http.handler.IProductHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.adapters.driving.http.controller.utils.ProductRestControllerConstants.PRODUCT_CONTROLLER_REQUEST_MAPPING;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT_CONTROLLER_REQUEST_MAPPING)
public class ProductRestController {
    private final IProductHandler productHandler;

    @GetMapping
    public PaginationResponseDto<ProductResponseDto> getProductFeed() {
        return productHandler.getProductFeed();
    }
}
