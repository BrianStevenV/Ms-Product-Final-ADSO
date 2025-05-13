package com.example.demo.adapters.driving.http.dto.request;

public record AddProductQuantityInventoryRequestDto(
        Long productId,
        Integer quantity
) {
}
