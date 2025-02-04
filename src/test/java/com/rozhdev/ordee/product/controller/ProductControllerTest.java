package com.rozhdev.ordee.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rozhdev.ordee.commons.config.ObjectMapperConfig;
import com.rozhdev.ordee.product.dto.CreateProductDto;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import({ObjectMapperConfig.class})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturnProductDto_whenValidRequest() throws Exception {
        CreateProductDto createProductDto = new CreateProductDto("Test Product", BigDecimal.valueOf(50.50), "Test Description");
        LocalDateTime createdAt = LocalDateTime.of(2025, 1, 1, 1, 1, 1);
        ProductDto productDto = new ProductDto(1L, "Test Product", BigDecimal.valueOf(50.50),
                "Test Description", createdAt, null);

        when(productService.create(any(CreateProductDto.class))).thenReturn(productDto);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProductDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(50.50))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.createdAt").value("2025-01-01 01:01:01"))
                .andExpect(jsonPath("$.updated_at").doesNotHaveJsonPath());
        verify(productService).create(any(CreateProductDto.class));
    }

    @Test
    void create_shouldReturnErrorDtoInvalidStatus_whenRequestIsNotValid() throws Exception {
        CreateProductDto invalidDto = new CreateProductDto("", BigDecimal.ZERO, "");

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().is(422))
                .andExpect(jsonPath("$.status").value("INVALID"))
                .andExpect(jsonPath("$.code").value("422"))
                .andExpect(jsonPath("$.message").value("Invalid input data"))
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors[?(@.field == 'name')].message").value("must not be blank"))
                .andExpect(jsonPath("$.errors[?(@.field == 'price')].message").value("must be greater than 0"));
    }
}