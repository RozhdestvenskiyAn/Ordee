package com.rozhdev.ordee.product.controller;

import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.rozhdev.ordee.utils.data.generator.ProductGenerator.generateNewProductDto;
import static com.rozhdev.ordee.utils.data.generator.ProductGenerator.generateProductDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;
    private final ProductDto newProductDto = generateNewProductDto();
    private final ProductDto productDto = generateProductDto();

    @Test
    void save_shouldReturnSavedProductDto_whenProductDtoIsValid() {
        when(productService.save(newProductDto)).thenReturn(productDto);

        ProductDto actual = productController.save(newProductDto);

        assertThat(actual).isEqualTo(productDto);
    }

    @Test
    void findAll_shouldReturnListOfProductDtos_whenProductsExist() {
        when(productService.findAll()).thenReturn(List.of(productDto, productDto));

        List<ProductDto> actual = productController.findAll();

        assertThat(actual).isEqualTo(List.of(productDto, productDto));
    }

    @Test
    void findAll_shouldReturnEmptyList_whenProductsDoesNotExist() {
        when(productService.findAll()).thenReturn(new ArrayList<>());

        List<ProductDto> actual = productController.findAll();

        assertThat(actual).isEmpty();
    }
}