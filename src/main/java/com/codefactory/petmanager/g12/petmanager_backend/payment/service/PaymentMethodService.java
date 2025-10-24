package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentMethodRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMethodMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentMethodDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentMethod;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository repository;
    private final PaymentMethodMapper mapper;

    @Transactional(readOnly = true)
    public List<PaymentMethodDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public PaymentMethodDTO getById(int id) {
        PaymentMethod entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado con id: " + id));
        return mapper.toDTO(entity);
    }

    @Transactional
    public PaymentMethodDTO create(PaymentMethodDTO dto) {
        dto.setId(null); // Evitar problemas al crear
        PaymentMethod saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Transactional
    public PaymentMethodDTO update(int id, PaymentMethodDTO dto) {
        PaymentMethod existing = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado con id: " + id));

        mapper.updateEntityFromDTO(dto, existing);
        PaymentMethod updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Método de pago no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}