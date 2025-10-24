package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentConditionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Condiciones de pago", description = "Operaciones CRUD relacionadas a las condiciones de pago")
@RestController
@RequestMapping("/api/payment-conditions")
@RequiredArgsConstructor
public class PaymentConditionController {

    private final PaymentConditionService paymentConditionService;

    @Operation(summary = "Listar condiciones de pago", description = "Devuelve todas las condiciones de pago")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentConditionResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<PaymentConditionResponseDTO>> getAllPaymentConditions() {
        List<PaymentConditionResponseDTO> conditions = paymentConditionService.findAll();
        return ResponseEntity.ok(conditions);
    }

    @Operation(summary = "Obtener condición de pago por ID", description = "Devuelve la condición de pago con el ID especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Condición encontrada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentConditionResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Condición no encontrada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentConditionResponseDTO> getPaymentConditionById(@PathVariable int id) {
        PaymentConditionResponseDTO condition = paymentConditionService.findById(id);
        return ResponseEntity.ok(condition);
    }

    @Operation(summary = "Crear condición de pago", description = "Crea una nueva condición de pago. Requiere rol ADMIN o MANAGER.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Condición creada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentConditionResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<PaymentConditionResponseDTO> createPaymentCondition(
            @Valid @RequestBody PaymentConditionRequestDTO dto) {
        PaymentConditionResponseDTO created = paymentConditionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Actualizar condición de pago", description = "Actualiza una condición de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Condición actualizada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentConditionResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Condición no encontrada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentConditionResponseDTO> updatePaymentCondition(
            @PathVariable int id, @Valid @RequestBody PaymentConditionRequestDTO dto) {
        PaymentConditionResponseDTO updated = paymentConditionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Eliminar condición de pago", description = "Elimina una condición de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Condición eliminada"),
        @ApiResponse(responseCode = "404", description = "Condición no encontrada",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentCondition(@PathVariable int id) {
        paymentConditionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}