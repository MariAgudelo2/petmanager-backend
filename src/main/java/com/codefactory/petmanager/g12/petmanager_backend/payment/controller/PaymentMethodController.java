package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentMethodService;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentMethodDTO;

import jakarta.validation.Valid;

@Tag(name = "Métodos de Pago", description = "Operaciones relacionadas a los métodos de pago")
@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService service;

    @Operation(summary = "Listar métodos de pago")
    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener método de pago por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear método de pago")
    @PostMapping
    public ResponseEntity<PaymentMethodDTO> create(
        @Valid @RequestBody PaymentMethodDTO dto
    ) {
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Actualizar método de pago")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> update(
        @PathVariable int id, 
        @Valid @RequestBody PaymentMethodDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar método de pago")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}