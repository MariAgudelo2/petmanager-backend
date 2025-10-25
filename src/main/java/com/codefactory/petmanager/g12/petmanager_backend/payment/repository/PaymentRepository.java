package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findBySupplierName(String name);
}
