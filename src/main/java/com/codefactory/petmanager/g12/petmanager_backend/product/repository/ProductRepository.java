package com.codefactory.petmanager.g12.petmanager_backend.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}