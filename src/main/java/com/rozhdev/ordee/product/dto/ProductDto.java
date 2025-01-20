package com.rozhdev.ordee.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDto(
        Long id,
        String name,
        BigDecimal price,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
