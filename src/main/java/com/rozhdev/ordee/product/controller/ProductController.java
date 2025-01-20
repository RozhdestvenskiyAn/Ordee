package com.rozhdev.ordee.product.controller;

import com.rozhdev.ordee.product.dto.CreateProductDto;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto create(CreateProductDto createProductDto) {
        return productService.create(createProductDto);
    }
}
