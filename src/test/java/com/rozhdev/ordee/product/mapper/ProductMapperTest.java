package com.rozhdev.ordee.product.mapper;

import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rozhdev.ordee.utils.data.generator.ProductGenerator.generateProduct;
import static com.rozhdev.ordee.utils.data.generator.ProductGenerator.generateProductDto;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {
    @InjectMocks
    private ProductMapperImpl productMapper;

    private final ProductDto productDto = generateProductDto();
    private final Product product = generateProduct();

    @Test
    void map_shouldReturnProduct() {
        Product actual = productMapper.map(productDto);

        assertThat(actual.getId()).isEqualTo(productDto.id());
        assertThat(actual.getName()).isEqualTo(productDto.name());
        assertThat(actual.getPrice()).isEqualTo(productDto.price());
    }

    @Test
    void map_shouldReturnProductDto() {
        ProductDto actual = productMapper.map(product);

        assertThat(actual.id()).isEqualTo(product.getId());
        assertThat(actual.name()).isEqualTo(product.getName());
        assertThat(actual.price()).isEqualTo(product.getPrice());
    }
}