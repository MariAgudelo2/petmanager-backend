package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentTermDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentTermService;
import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;

import jakarta.validation.Valid;

@Tag(name = "Términos de Pago", description = "Operaciones relacionadas a los términos de pago")
@RestController
@RequestMapping("/api/payment-terms")
@RequiredArgsConstructor
public class PaymentTermController {

    private final PaymentTermService service;

    @Operation(summary = "Listar todos los términos de pago")
    @ApiResponse(responseCode = "200", content = @Content(
        schema = @Schema(implementation = PaymentTermDTO.class)))
    @GetMapping
    public ResponseEntity<List<PaymentTermDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener término de pago por ID")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<PaymentTermDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear nuevo término de pago")
    @PostMapping
    @ApiResponse(responseCode = "201", content = @Content(
        schema = @Schema(implementation = PaymentTermDTO.class)))
    public ResponseEntity<PaymentTermDTO> create(
        @Valid @RequestBody PaymentTermDTO dto
    ) {
        PaymentTermDTO created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Actualizar término de pago existente")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<PaymentTermDTO> update(
        @PathVariable int id,
        @Valid @RequestBody PaymentTermDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar término de pago")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}