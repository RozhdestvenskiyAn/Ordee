package com.rozhdev.ordee.product.service;

import com.rozhdev.ordee.product.dto.CreateProductDto;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.entity.Product;
import com.rozhdev.ordee.product.mapper.ProductMapper;
import com.rozhdev.ordee.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto create(CreateProductDto createProductDto) {
        Product product = productMapper.map(createProductDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.map(savedProduct);
    }
}
