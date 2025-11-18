package com.codefactory.petmanager.g12.petmanager_backend.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.SuppliersProducts;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;


public interface SuppliersProductsRepository extends JpaRepository<SuppliersProducts, Integer>{
    List<SuppliersProducts> findBySupplier(Supplier supplier);
}
