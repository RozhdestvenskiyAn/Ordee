package com.rozhdev.ordee.product.service;

import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.entity.Product;
import com.rozhdev.ordee.product.mapper.ProductMapper;
import com.rozhdev.ordee.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.rozhdev.ordee.utils.data.generator.ProductGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    private final ProductDto newProductDto = generateNewProductDto();
    private final Product newProduct = generateNewProduct();
    private final Product product = generateProduct();
    private final ProductDto productDto = generateProductDto();
    private final List<Product> products  = List.of(product, product);
    private final List<ProductDto> productDtos = List.of(productDto, productDto);

    @Test
    void save_shouldReturnSavedProductDto_whenProductDtoIsValid() {
        when(productMapper.map(newProductDto)).thenReturn(newProduct);
        when(productRepository.save(newProduct)).thenReturn(product);
        when(productMapper.map(product)).thenReturn(productDto);

        ProductDto actual = productService.save(newProductDto);

        assertThat(actual).isEqualTo(productDto);
    }

    @Test
    void findAll_shouldReturnListOfProductDtos_whenProductsExist() {
        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.map(products)).thenReturn(productDtos);

        List<ProductDto> actual = productService.findAll();

        assertThat(actual).isEqualTo(productDtos);
    }

    @Test
    void findAll_shouldReturnEmptyList_whenProductsDoesNotExist() {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        when(productMapper.map(new ArrayList<>())).thenReturn(new ArrayList<>());

        List<ProductDto> actual = productService.findAll();

        assertThat(actual).isEmpty();
    }
}