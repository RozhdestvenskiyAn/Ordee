package com.rozhdev.ordee.commons.exception;

public record ErrorDetail(
        String field,
        String message
) {
}
