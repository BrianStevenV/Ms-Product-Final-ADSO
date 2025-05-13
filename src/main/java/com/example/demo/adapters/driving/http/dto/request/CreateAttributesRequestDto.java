package com.example.demo.adapters.driving.http.dto.request;

import java.util.List;

public record CreateAttributesRequestDto(
        String nameAttribute,
        String typeDataOfValueAttribute,
        List<Long> categoryIdsList
) {
}
