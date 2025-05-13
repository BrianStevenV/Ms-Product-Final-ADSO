package com.example.demo.adapters.driving.http.dto.request;

import java.util.List;

public record CreateProductRequestDto(
        String name,
        String description,
        Double price,
        Long categoryId,
        Integer amountAboutInventory,
        List<ProductAttributesRequestDto> attributes
) {
}
