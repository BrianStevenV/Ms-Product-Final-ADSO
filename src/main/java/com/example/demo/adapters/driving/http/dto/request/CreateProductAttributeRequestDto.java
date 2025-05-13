package com.example.demo.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProductAttributeRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String type,
        @NotNull
        List<Long> categoryId
) {
}
