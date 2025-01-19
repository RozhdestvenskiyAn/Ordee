package com.rozhdev.ordee.utils.data.generator;

import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.entity.Product;

import java.math.BigDecimal;

public class ProductGenerator {

    public static Product generateNewProduct() {
        Product product = new Product();
        product.setName("product");
        product.setPrice(BigDecimal.valueOf(100.50));
        return product;
    }

    public static Product generateProduct() {
        Product product = generateNewProduct();
        product.setId(1L);
        return product;
    }

    public static ProductDto generateNewProductDto() {
        return new ProductDto(null, "product", BigDecimal.valueOf(100.50));
    }

    public static ProductDto generateProductDto() {
        return new ProductDto(1L, "product", BigDecimal.valueOf(100.50));
    }
}
