package com.codefactory.petmanager.g12.petmanager_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierUpdateDTO;
import com.codefactory.petmanager.g12.petmanager_backend.entities.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.mapper.SupplierMapper;
import com.codefactory.petmanager.g12.petmanager_backend.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {
    
  private final SupplierMapper supplierMapper;
  private final SupplierRepository supplierRepository;

  @Transactional(readOnly = true)
  public SupplierResponseDTO getSupplierById(int supplierId) {
      Supplier supplier = supplierRepository.findById(supplierId)
              .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + supplierId));
      return supplierMapper.supplierToSupplierResponseDTO(supplier);
  }

  @Transactional(readOnly = true)
  public SupplierResponseDTO getSupplierByNit(String nit) {
      Supplier supplier = supplierRepository.findByNit(nit)
              .orElseThrow(() -> new EntityNotFoundException("Supplier not found with NIT: " + nit));
      return supplierMapper.supplierToSupplierResponseDTO(supplier);
  }

  @Transactional(readOnly = true)
  public List<SupplierResponseDTO> getAllSuppliers() {
      List<Supplier> suppliers = supplierRepository.findAll();
      return supplierMapper.suppliersToSupplierResponseDTOs(suppliers);
  }

  @Transactional(readOnly = true)
  public List<SupplierResponseDTO> searchSuppliersByName(String name) {
      List<Supplier> suppliers = supplierRepository.findByNameContainingIgnoreCase(name);
      return supplierMapper.suppliersToSupplierResponseDTOs(suppliers);
  }

  @Transactional
  public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
      // Validate NIT uniqueness
      if (supplierRepository.existsByNit(supplierRequestDTO.getNit())) {
          throw new IllegalArgumentException("Supplier with NIT " + supplierRequestDTO.getNit() + " already exists");
      }
      
      // Validate name uniqueness
      Optional<Supplier> existingSupplier = supplierRepository.findByName(supplierRequestDTO.getName());
      if (existingSupplier.isPresent()) {
          throw new IllegalArgumentException("Supplier with name " + supplierRequestDTO.getName() + " already exists");
      }
      
      Supplier supplier = supplierMapper.supplierRequestDTOToSupplier(supplierRequestDTO);
      Supplier savedSupplier = supplierRepository.save(supplier);
      return supplierMapper.supplierToSupplierResponseDTO(savedSupplier);
  }

  @Transactional
  public SupplierResponseDTO updateSupplier(int supplierId, SupplierUpdateDTO supplierUpdateDTO) {
      Supplier existingSupplier = supplierRepository.findById(supplierId)
              .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + supplierId));
      
      // Update only non-null fields
      if (supplierUpdateDTO.getNit() != null) {
          // Check if NIT is being changed and if new NIT already exists
          if (!existingSupplier.getNit().equals(supplierUpdateDTO.getNit()) && 
              supplierRepository.existsByNit(supplierUpdateDTO.getNit())) {
              throw new IllegalArgumentException("Supplier with NIT " + supplierUpdateDTO.getNit() + " already exists");
          }
          existingSupplier.setNit(supplierUpdateDTO.getNit());
      }
      
      if (supplierUpdateDTO.getName() != null) {
          // Check if name is being changed and if new name already exists
          if (!existingSupplier.getName().equals(supplierUpdateDTO.getName()) && 
              supplierRepository.findByName(supplierUpdateDTO.getName()).isPresent()) {
              throw new IllegalArgumentException("Supplier with name " + supplierUpdateDTO.getName() + " already exists");
          }
          existingSupplier.setName(supplierUpdateDTO.getName());
      }
      
      if (supplierUpdateDTO.getPhoneNumber() != null) {
          existingSupplier.setPhoneNumber(supplierUpdateDTO.getPhoneNumber());
      }
      
      if (supplierUpdateDTO.getAddress() != null) {
          existingSupplier.setAddress(supplierUpdateDTO.getAddress());
      }
      
      Supplier updatedSupplier = supplierRepository.save(existingSupplier);
      return supplierMapper.supplierToSupplierResponseDTO(updatedSupplier);
  }

  @Transactional
  public void deleteSupplier(int supplierId) {
      if (!supplierRepository.existsById(supplierId)) {
          throw new EntityNotFoundException("Supplier not found with id: " + supplierId);
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