package com.example.demo.adapters.driving.http.handler;

import com.example.demo.adapters.driving.http.dto.response.PaginationResponseDto;
import com.example.demo.adapters.driving.http.dto.response.ProductResponseDto;

public interface IProductHandler {
    PaginationResponseDto<ProductResponseDto> getProductFeed();
}
