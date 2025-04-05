package com.example.demo.adapters.driving.http.dto.response;

import java.util.List;

public record ProductResponseDto(
        Long id,
        String title,
        String description,
        Double price,
        CategoryResponseDto category,
        List<ProductAttributeResponseDto> attributes,
        Long userProviderId
) {
}
