package com.codefactory.petmanager.g12.petmanager_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.SupplierUpdateDTO;
import com.codefactory.petmanager.g12.petmanager_backend.services.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  @GetMapping
  public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
    List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
    return ResponseEntity.ok(suppliers);
  }

  @GetMapping("/search")
  public ResponseEntity<List<SupplierResponseDTO>> searchSuppliers(@RequestParam String name) {
    List<SupplierResponseDTO> suppliers = supplierService.searchSuppliersByName(name);
    return ResponseEntity.ok(suppliers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable int id) {
    SupplierResponseDTO supplier = supplierService.getSupplierById(id);
    return ResponseEntity.ok(supplier);
  }

  @GetMapping("/nit/{nit}")
  public ResponseEntity<SupplierResponseDTO> getSupplierByNit(@PathVariable String nit) {
    SupplierResponseDTO supplier = supplierService.getSupplierByNit(nit);
    return ResponseEntity.ok(supplier);
  }

  @PostMapping
  public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO supplierRequestDTO) {
    SupplierResponseDTO createdSupplier = supplierService.createSupplier(supplierRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable int id, @Valid @RequestBody SupplierUpdateDTO supplierUpdateDTO) {
    SupplierResponseDTO updatedSupplier = supplierService.updateSupplier(id, supplierUpdateDTO);
    return ResponseEntity.ok(updatedSupplier);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteSupplier(@PathVariable int id) {
    supplierService.deleteSupplier(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/exists/nit/{nit}")
  public ResponseEntity<Boolean> existsByNit(@PathVariable String nit) {
    boolean exists = supplierService.existsByNit(nit);
    return ResponseEntity.ok(exists);
  }

  @GetMapping("/exists/name/{name}")
  public ResponseEntity<Boolean> existsByName(@PathVariable String name) {
    boolean exists = supplierService.existsByName(name);
    return ResponseEntity.ok(exists);
  }
}