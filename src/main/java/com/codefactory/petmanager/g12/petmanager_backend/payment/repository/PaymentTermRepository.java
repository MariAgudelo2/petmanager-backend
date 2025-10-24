package com.codefactory.petmanager.g12.petmanager_backend.payment.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentTerm;

@Repository
public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Integer> {

    Optional<PaymentTerm> findByDescription(String description);

    boolean existsByDescription(String description);

    List<PaymentTerm> findAll();

}