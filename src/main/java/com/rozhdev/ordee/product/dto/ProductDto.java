package com.rozhdev.ordee.product.dto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        BigDecimal price
) {}
