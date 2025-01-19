package com.rozhdev.ordee.product.controller;

import com.rozhdev.ordee.commons.validation.Create;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto save(@Validated(Create.class) @RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }
}
