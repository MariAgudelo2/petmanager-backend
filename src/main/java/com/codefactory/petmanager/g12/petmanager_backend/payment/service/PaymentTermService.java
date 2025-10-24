package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentTermRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentTermMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentTermDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentTerm;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentTermService {

    private final PaymentTermRepository repository;
    private final PaymentTermMapper mapper;

    @Transactional(readOnly = true)
    public List<PaymentTermDTO> getAll() {
        List<PaymentTerm> list = repository.findAll();
        return mapper.toDTOList(list);
    }

    @Transactional(readOnly = true)
    public PaymentTermDTO getById(int id) {
        PaymentTerm paymentTerm = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Término de pago no encontrado con id: " + id));
        return mapper.toDTO(paymentTerm);
    }

    @Transactional
    public PaymentTermDTO create(PaymentTermDTO dto) {
        PaymentTerm entity = mapper.toEntity(dto);
        PaymentTerm saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Transactional
    public PaymentTermDTO update(int id, PaymentTermDTO dto) {
        PaymentTerm existing = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Término de pago no encontrado con id: " + id));

        mapper.updateEntityFromDTO(dto, existing);

        PaymentTerm updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Término de pago no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}