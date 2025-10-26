package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;
import java.util.List;


public interface PaymentsProductsRepository extends JpaRepository<PaymentsProducts, Integer> {
  List<PaymentsProducts> findByPayment(Payment payment);
}
