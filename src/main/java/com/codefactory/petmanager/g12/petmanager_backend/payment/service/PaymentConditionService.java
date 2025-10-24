package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentConditionMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentConditionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentConditionService {

    private final PaymentConditionRepository paymentConditionRepository;
    private final PaymentConditionMapper paymentConditionMapper;

    public List<PaymentConditionResponseDTO> findAll() {
        List<PaymentCondition> conditions = paymentConditionRepository.findAll();
        return paymentConditionMapper.toResponseDTOList(conditions);
    }

    public PaymentConditionResponseDTO findById(Integer id) {
        PaymentCondition condition = paymentConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condición de pago no encontrada"));
        return paymentConditionMapper.toResponseDTO(condition);
    }

    public PaymentConditionResponseDTO create(PaymentConditionRequestDTO dto) {
        PaymentCondition condition = paymentConditionMapper.toEntity(dto);
        PaymentCondition saved = paymentConditionRepository.save(condition);
        return paymentConditionMapper.toResponseDTO(saved);
    }

    public PaymentConditionResponseDTO update(Integer id, PaymentConditionRequestDTO dto) {
        PaymentCondition existing = paymentConditionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condición de pago no encontrada"));
        PaymentCondition updated = paymentConditionMapper.toEntity(dto);
        updated.setId(existing.getId());
        PaymentCondition saved = paymentConditionRepository.save(updated);
        return paymentConditionMapper.toResponseDTO(saved);
    }

    public void delete(Integer id) {
        if (!paymentConditionRepository.existsById(id)) {
            throw new RuntimeException("Condición de pago no encontrada");
        }
        paymentConditionRepository.deleteById(id);
    }
}