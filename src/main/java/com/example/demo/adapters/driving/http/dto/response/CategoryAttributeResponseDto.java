package com.example.demo.adapters.driving.http.dto.response;

public record CategoryAttributeResponseDto(
        Long id,
        CategoryResponseDto category, // TODO: Or Category Entity
        DefinitionAttributeResponseDto definitionAttribute
) {
}
