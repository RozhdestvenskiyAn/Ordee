package com.rozhdev.ordee.product.dto;

import com.rozhdev.ordee.commons.validation.Create;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDto(
        @Null(groups = Create.class)
        Long id,
        @NotBlank
        String name,
        @Positive(message = "Price must be positive")
        BigDecimal price
) {}
