package com.codefactory.petmanager.g12.petmanager_backend.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  boolean existsByNameIgnoreCaseAndBrandIgnoreCase(String name, String brand);
  Product findByNameIgnoreCaseAndBrandIgnoreCase(String name, String brand);
}
