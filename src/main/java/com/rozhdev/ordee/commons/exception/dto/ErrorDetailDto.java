package com.rozhdev.ordee.commons.exception.dto;

public record ErrorDetailDto(
        String field,
        String message
) {
}
