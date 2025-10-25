package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.paymentsToPaymentResponseDTOs(payments);
    }

    @Transactional(readOnly = true)
    public PaymentResponseDTO getById(int id) {
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró el pago con id: " + id));
        return paymentMapper.paymentToPaymentResponseDTO(payment);
    }

    @Transactional
    public PaymentResponseDTO create(PaymentRequestDTO dto) {
        Payment payment = paymentMapper.paymentRequestDTOToPayment(dto);
        payment.setPaymentDate(LocalDate.now()); // Fecha asignada automáticamente
        Payment saved = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentResponseDTO(saved);
    }

    @Transactional
    public PaymentResponseDTO update(int id, PaymentRequestDTO dto) {
        Payment existing = paymentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No se encontró el pago con id: " + id));

        existing.setAmount(dto.getAmount());
        existing.setNotes(dto.getNotes());

        Payment updated = paymentRepository.save(existing);
        return paymentMapper.paymentToPaymentResponseDTO(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!paymentRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el pago con id: " + id);
        }
        paymentRepository.deleteById(id);
    }
}