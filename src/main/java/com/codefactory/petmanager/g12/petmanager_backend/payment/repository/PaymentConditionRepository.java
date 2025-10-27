package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;

public interface PaymentConditionRepository extends JpaRepository<PaymentCondition, Integer> {
    
}
