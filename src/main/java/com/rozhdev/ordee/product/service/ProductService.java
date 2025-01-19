package com.rozhdev.ordee.product.service;

import com.rozhdev.ordee.commons.service.EntityService;
import com.rozhdev.ordee.product.dto.ProductDto;
import com.rozhdev.ordee.product.entity.Product;
import com.rozhdev.ordee.product.mapper.ProductMapper;
import com.rozhdev.ordee.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements EntityService<ProductDto> {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productMapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.map(savedProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.map(products);
    }
}
