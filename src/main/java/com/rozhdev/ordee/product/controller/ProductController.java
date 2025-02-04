package com.rozhdev.ordee.product.controller;

import com.rozhdev.ordee.product.dto.CreateProductDto;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@Valid @RequestBody CreateProductDto createProductDto) {
        return productService.create(createProductDto);
    }
}
