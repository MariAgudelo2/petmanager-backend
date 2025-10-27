package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findBySupplierName(String name);
    List<Payment> findBySupplier(Supplier supplier);
    Optional<Payment> findTopBySupplierAndPaymentDateLessThanEqualOrderByPaymentDateDesc(Supplier supplier, LocalDate date);
    Optional<Payment> findTopBySupplierAndPaymentDateAfterOrderByPaymentDateAsc(Supplier supplier, LocalDate date);
}
