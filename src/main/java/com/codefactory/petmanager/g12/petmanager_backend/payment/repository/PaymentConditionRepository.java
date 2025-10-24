package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;

@Repository
public interface PaymentConditionRepository extends JpaRepository<PaymentCondition, Integer> {
}