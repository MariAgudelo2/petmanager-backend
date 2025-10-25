package com.codefactory.petmanager.g12.petmanager_backend.supplier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentConditionRepository;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierUpdateDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.mapper.SupplierMapper;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.SupplierRepository;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.specification.SupplierSpecification;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    
  private final SupplierMapper supplierMapper;
  private final SupplierRepository supplierRepository;
  private final PaymentConditionRepository paymentConditionRepository;

  @Transactional(readOnly = true)
  public SupplierResponseDTO getSupplierById(int supplierId) {
      Supplier supplier = supplierRepository.findById(supplierId)
              .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un proveedor con id: " + supplierId));
      return supplierMapper.supplierToSupplierResponseDTO(supplier);
  }

  @Transactional(readOnly = true)
  public SupplierResponseDTO getSupplierByNit(String nit) {
      Supplier supplier = supplierRepository.findByNit(nit)
              .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un proveedor con NIT: " + nit));
      return supplierMapper.supplierToSupplierResponseDTO(supplier);
  }

  @Transactional(readOnly = true)
  public List<SupplierResponseDTO> getAllSuppliers() {
      List<Supplier> suppliers = supplierRepository.findAll();
      return supplierMapper.suppliersToSupplierResponseDTOs(suppliers);
  }

  @Transactional(readOnly = true)
  public Page<SupplierResponseDTO> searchSuppliers(
    String name,
    String nit,
    String address,
    Integer paymentConditionId,
    Pageable pageable) {

    Specification<Supplier> spec = Specification
            .where(SupplierSpecification.nameContains(name))
            .and(SupplierSpecification.nitContains(nit))
            .and(SupplierSpecification.addressContains(address))
            .and(SupplierSpecification.paymentConditionEquals(paymentConditionId));

    Page<Supplier> resultPage = supplierRepository.findAll(spec, pageable);
    return resultPage.map(supplierMapper::supplierToSupplierResponseDTO);
  }

  @Transactional(readOnly = true)
  public List<SupplierResponseDTO> searchSuppliersByName(String name) {
      List<Supplier> suppliers = supplierRepository.findByNameContainingIgnoreCase(name);
      return supplierMapper.suppliersToSupplierResponseDTOs(suppliers);
  }

  @Transactional
  public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
      if (supplierRepository.existsByNit(supplierRequestDTO.getNit())) {
          throw new IllegalArgumentException("Un proveedor con NIT " + supplierRequestDTO.getNit() + " ya existe!");
      }
      
      Optional<Supplier> existingSupplier = supplierRepository.findByName(supplierRequestDTO.getName());
      if (existingSupplier.isPresent()) {
          throw new IllegalArgumentException("Un proveedor con el nombre " + supplierRequestDTO.getName() + " ya existe!");
      }

      int paymentConditionId = supplierRequestDTO.getPaymentConditionId() != null ? supplierRequestDTO.getPaymentConditionId() : 1;

      PaymentCondition paymentCondition = paymentConditionRepository.findById(paymentConditionId)
                    .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado una condición de pago con id: " + supplierRequestDTO.getPaymentConditionId()));
      
      Supplier supplier = supplierMapper.supplierRequestDTOToSupplier(supplierRequestDTO);
      supplier.setPaymentCondition(paymentCondition);

      Supplier savedSupplier = supplierRepository.save(supplier);
      return supplierMapper.supplierToSupplierResponseDTO(savedSupplier);
  }

  @Transactional
  public SupplierResponseDTO updateSupplier(int supplierId, SupplierUpdateDTO supplierUpdateDTO) {
      Supplier existingSupplier = supplierRepository.findById(supplierId)
              .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un proveedor con id: " + supplierId));
      
      if (supplierUpdateDTO.getNit() != null) {
          if (!existingSupplier.getNit().equals(supplierUpdateDTO.getNit()) && 
              supplierRepository.existsByNit(supplierUpdateDTO.getNit())) {
              throw new IllegalArgumentException("Un proveedor con NIT " + supplierUpdateDTO.getNit() + " ya existe!");
          }
          existingSupplier.setNit(supplierUpdateDTO.getNit());
      }
      
      if (supplierUpdateDTO.getName() != null) {
          if (!existingSupplier.getName().equals(supplierUpdateDTO.getName()) && 
              supplierRepository.findByName(supplierUpdateDTO.getName()).isPresent()) {
              throw new IllegalArgumentException("Un proveedor con el nombre " + supplierUpdateDTO.getName() + " ya existe!");
          }
          existingSupplier.setName(supplierUpdateDTO.getName());
      }
      
      if (supplierUpdateDTO.getPhoneNumber() != null) {
          existingSupplier.setPhoneNumber(supplierUpdateDTO.getPhoneNumber());
      }
      
      if (supplierUpdateDTO.getAddress() != null) {
          existingSupplier.setAddress(supplierUpdateDTO.getAddress());
      }

      if (supplierUpdateDTO.getPaymentConditionId() != null) {
          PaymentCondition newPaymentCondition = paymentConditionRepository.findById(supplierUpdateDTO.getPaymentConditionId())
                        .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado una condición de pago con id: " + supplierUpdateDTO.getPaymentConditionId()));
          existingSupplier.setPaymentCondition(newPaymentCondition);
      }

      if (supplierUpdateDTO.getPaymentNotes() != null) {
          existingSupplier.setPaymentNotes(supplierUpdateDTO.getPaymentNotes());
      }
      
      Supplier updatedSupplier = supplierRepository.save(existingSupplier);
      return supplierMapper.supplierToSupplierResponseDTO(updatedSupplier);
  }

  @Transactional
  public void deleteSupplier(int supplierId) {
      if (!supplierRepository.existsById(supplierId)) {
          throw new EntityNotFoundException("No se ha encontrado un proveedor con id: " + supplierId);
      }
      supplierRepository.deleteById(supplierId);
  }

  @Transactional(readOnly = true)
  public boolean existsByNit(String nit) {
      return supplierRepository.existsByNit(nit);
  }

  @Transactional(readOnly = true)
  public boolean existsByName(String name) {
      return supplierRepository.findByName(name).isPresent();
  }
}