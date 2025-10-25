package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentService;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;

import jakarta.validation.Valid;

@Tag(name = "Pagos", description = "Operaciones relacionadas a los pagos")
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @Operation(summary = "Listar pagos", description = "Devuelve todos los pagos registrados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener pago por ID", description = "Devuelve un pago específico según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pago encontrado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Registrar un pago", description = "Crea un nuevo pago. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pago registrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Actualizar un pago", description = "Actualiza un pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pago actualizado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> update(
            @PathVariable int id,
            @Valid @RequestBody PaymentRequestDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar un pago", description = "Elimina un pago registrado. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Pago eliminado"),
        @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
