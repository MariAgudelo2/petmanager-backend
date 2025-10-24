package com.codefactory.petmanager.g12.petmanager_backend.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.codefactory.petmanager.g12.petmanager_backend.product.repository.ProductRepository;
import com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.product.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public List<ProductDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    public ProductDTO getById(int id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return mapper.toDTO(product);
    }

    public ProductDTO create(ProductDTO dto) {
        Product entity = mapper.toEntity(dto);
        Product saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    public ProductDTO update(int id, ProductDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        existing.setName(dto.getName());
        existing.setBrand(dto.getBrand());
        existing.setSalePrice(dto.getSalePrice());
        existing.setDescription(dto.getDescription());

        Product updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    public void delete(int id) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        repository.delete(existing);
    }
}
