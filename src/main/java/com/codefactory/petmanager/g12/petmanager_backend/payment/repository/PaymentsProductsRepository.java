package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;

public interface PaymentsProductsRepository extends JpaRepository<PaymentsProducts, Integer> {
    
}
