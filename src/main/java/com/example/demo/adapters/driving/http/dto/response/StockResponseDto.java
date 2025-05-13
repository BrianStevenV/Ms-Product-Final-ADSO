package com.example.demo.adapters.driving.http.dto.response;

import java.time.LocalDateTime;

public record StockResponseDto(
        Long id,
        String productName,
        Double price,
        Integer quantity,
        LocalDateTime openDateAt,
        LocalDateTime lastModifiedAt,
        String categoryName,
        Boolean isActive
        //TODO: Return to promotion when it is implemented
) {
}
