package com.codefactory.petmanager.g12.petmanager_backend.contract.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.mapper.ContractMapper;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.Contract;
import com.codefactory.petmanager.g12.petmanager_backend.contract.repository.ContractRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final ContractMapper mapper;

    @Transactional(readOnly = true)
    public List<ContractResponseDTO> getAll() {
        List<Contract> contracts = contractRepository.findAll();
        return mapper.toResponseDTOList(contracts);
    }

    @Transactional(readOnly = true)
    public ContractResponseDTO getById(int id) {
        Contract contract = contractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contrato no encontrado con id: " + id));
        return mapper.toResponseDTO(contract);
    }

    @Transactional
    public ContractResponseDTO create(ContractRequestDTO dto) {
        Contract contract = mapper.toEntity(dto);
        Contract saved = contractRepository.save(contract);
        return mapper.toResponseDTO(saved);
    }

    @Transactional
    public ContractResponseDTO update(int id, ContractRequestDTO dto) {
        Contract existing = contractRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contrato no encontrado con id: " + id));

        mapper.updateEntityFromDTO(dto, existing);
        Contract updated = contractRepository.save(existing);

        return mapper.toResponseDTO(updated);
    }

    @Transactional
    public void delete(int id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contrato no encontrado con id: " + id);
        }
        contractRepository.deleteById(id);
    }
}