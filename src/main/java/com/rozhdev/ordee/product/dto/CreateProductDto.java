package com.rozhdev.ordee.product.dto;

import java.math.BigDecimal;

public record CreateProductDto(
        String name,
        BigDecimal price,
        String description
) {
}
