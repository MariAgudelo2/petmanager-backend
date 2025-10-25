package com.codefactory.petmanager.g12.petmanager_backend.contract.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.mapper.ContractProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractProduct;
import com.codefactory.petmanager.g12.petmanager_backend.contract.repository.ContractProductRepository;
import com.codefactory.petmanager.g12.petmanager_backend.contract.repository.ContractRepository;
import com.codefactory.petmanager.g12.petmanager_backend.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ContractProductService {

    private final ContractProductRepository repository;
    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;
    private final ContractProductMapper mapper;

    @Transactional(readOnly = true)
    public List<ContractProductResponseDTO> getAll() {
        return mapper.toResponseDTOList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public ContractProductResponseDTO getById(int id) {
        ContractProduct entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato producto no encontrado con ID: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ContractProductResponseDTO create(ContractProductRequestDTO dto) {
        var contract = contractRepository.findById(dto.getContractId())
            .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + dto.getContractId()));

        var product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + dto.getProductId()));

        ContractProduct entity = mapper.toEntity(dto);
        entity.setContract(contract);
        entity.setProduct(product);

        ContractProduct saved = repository.save(entity);
        return mapper.toResponseDTO(saved);
    }

    public ContractProductResponseDTO update(int id, ContractProductRequestDTO dto) {
        ContractProduct entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato producto no encontrado con ID: " + id));

        var contract = contractRepository.findById(dto.getContractId())
            .orElseThrow(() -> new IllegalArgumentException("Contrato no encontrado con ID: " + dto.getContractId()));

        var product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + dto.getProductId()));

        mapper.updateEntityFromDTO(dto, entity);
        entity.setContract(contract);
        entity.setProduct(product);

        ContractProduct updated = repository.save(entity);
        return mapper.toResponseDTO(updated);
    }

    public void delete(int id) {
        ContractProduct entity = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Contrato producto no encontrado con ID: " + id));
        repository.delete(entity);
    }
}
