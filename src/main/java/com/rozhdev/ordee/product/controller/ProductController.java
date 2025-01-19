package com.rozhdev.ordee.product.controller;

import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }
}
