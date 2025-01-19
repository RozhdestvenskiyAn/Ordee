package com.rozhdev.ordee.product.dto;

import com.rozhdev.ordee.commons.validation.Create;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public record ProductDto(
        @Null(groups = Create.class)
        Long id,
        @NotBlank
        String name,
        @NotNull @Min(value = 0, message = "Price must be positive")
        BigDecimal price
) {}
