package com.rozhdev.ordee.commons.exception.handler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.rozhdev.ordee.commons.exception.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleMethodArgumentNotValidException_shouldReturnErrorDto_whenValidationFails() {
        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError1 = new FieldError("objectName", "field1", "must not be blank");
        FieldError fieldError2 = new FieldError("objectName", "field2", "must be a positive number");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);


        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleMethodArgumentNotValidException(exception);


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody()).isNotNull();

        ErrorDto errorDto = response.getBody();
        assertThat(errorDto.getMessage()).isEqualTo("Invalid input data");
        assertThat(errorDto.getCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY.value());

        assertThat(errorDto.getErrors())
                .hasSize(2)
                .extracting("field", "message")
                .containsExactlyInAnyOrder(
                        tuple("field1", "must not be blank"),
                        tuple("field2", "must be a positive number")
                );
    }

    @Test
    void handleHttpMessageNotReadableException_shouldReturnErrorDto_whenThrowJsonMappingException() {
        JsonMappingException.Reference reference1 = new JsonMappingException.Reference(null, "unknownField1");
        JsonMappingException.Reference reference2 = new JsonMappingException.Reference(null, "unknownField2");

        JsonMappingException cause = mock(JsonMappingException.class);
        when(cause.getPath()).thenReturn(List.of(reference1, reference2));
        HttpInputMessage mockHttpInputMessage = mock(HttpInputMessage.class);
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Invalid JSON", cause, mockHttpInputMessage);


        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleHttpMessageNotReadableException(exception);


        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();

        ErrorDto errorDto = response.getBody();
        assertThat(errorDto.getMessage()).isEqualTo("Invalid json format");
        assertThat(errorDto.getCode()).isEqualTo(400);

        assertThat(errorDto.getErrors())
                .hasSize(2)
                .extracting("field", "message")
                .containsExactlyInAnyOrder(
                        tuple("unknownField1", "Unknown field"),
                        tuple("unknownField2", "Unknown field")
                );
    }

    @Test
    void handleHttpMessageNotReadableException_shouldReturnEmptyErrors_whenNoJsonMappingException() {
        HttpInputMessage mockHttpInputMessage = mock(HttpInputMessage.class);
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("dummy message", null, mockHttpInputMessage);


        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleHttpMessageNotReadableException(exception);


        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();

        ErrorDto errorDto = response.getBody();
        assertThat(errorDto.getMessage()).isEqualTo("Invalid json format");
        assertThat(errorDto.getCode()).isEqualTo(400);
        assertThat(errorDto.getErrors()).isEmpty();
    }

    @Test
    void handleGeneralException() {
        Exception exception = new Exception("dummy message");

        ResponseEntity<ErrorDto> response = globalExceptionHandler.handleGeneralException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody()).isNotNull();

        ErrorDto errorDto = response.getBody();
        assertThat(errorDto.getMessage()).isEqualTo("Internal Server Error");
        assertThat(errorDto.getCode()).isEqualTo(500);
        assertThat(errorDto.getErrors()).isEmpty();
    }
}