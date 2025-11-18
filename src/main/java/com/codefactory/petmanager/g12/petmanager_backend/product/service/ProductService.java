package com.codefactory.petmanager.g12.petmanager_backend.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.product.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    @Transactional
    public Product findOrCreateProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        
        if (productRepository.existsByNameIgnoreCaseAndBrandIgnoreCase(
                product.getName(), 
                product.getBrand())) {
            return productRepository.findByNameIgnoreCaseAndBrandIgnoreCase(
                product.getName(), 
                product.getBrand()
            );
        }
        
        return productRepository.save(product);
    }
}