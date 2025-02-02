package com.rozhdev.ordee.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateProductDto(
        @NotBlank
        String name,
        @NotNull
        @Positive
        BigDecimal price,
        String description
) {
}
