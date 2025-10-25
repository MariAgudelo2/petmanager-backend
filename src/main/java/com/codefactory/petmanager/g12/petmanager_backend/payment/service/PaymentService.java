package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentRepository;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SupplierRepository supplierRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Optional<Supplier> existingSupplier = supplierRepository.findByNit(paymentRequestDTO.getSupplier().getNit());
        if (existingSupplier.isEmpty()) {
            throw new IllegalArgumentException("Proveedor no encontrado: " + paymentRequestDTO.getSupplier().getNit());
        }

        Supplier supplier = existingSupplier.get();
        Payment payment = new Payment();
        
        payment.setSupplier(supplier);
        payment.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setNotes(paymentRequestDTO.getNotes());

        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentResponseDTO(savedPayment);
    }

} 
    
