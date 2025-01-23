package com.rozhdev.ordee.commons.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rozhdev.ordee.commons.exception.dto.ErrorDetailDto;
import com.rozhdev.ordee.commons.exception.dto.ErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.rozhdev.ordee.commons.exception.Status.ERROR;
import static com.rozhdev.ordee.commons.exception.Status.INVALID;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDetailDto> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorDetailDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        ErrorDto errorDto = new ErrorDto(INVALID, UNPROCESSABLE_ENTITY.value(), "Invalid input data", errors);
        return ResponseEntity
                .status(UNPROCESSABLE_ENTITY)
                .body(errorDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        List<ErrorDetailDto> errors = List.of();
        if (ex.getCause() instanceof JsonMappingException jsonMappingException) {
            errors = jsonMappingException.getPath().stream()
                    .map(reference -> new ErrorDetailDto(reference.getFieldName(), "Unknown field"))
                    .toList();
        }
        ErrorDto errorDto = new ErrorDto(ERROR, BAD_REQUEST.value(), "Invalid json format", errors);
        return ResponseEntity
                .badRequest()
                .body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorDto errorDto = new ErrorDto(ERROR, INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(errorDto);
    }
}
