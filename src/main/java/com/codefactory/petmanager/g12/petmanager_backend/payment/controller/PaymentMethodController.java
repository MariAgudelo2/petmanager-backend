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

import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentMethodService;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentMethodDTO;

import jakarta.validation.Valid;

@Tag(name = "Métodos de Pago", description = "Operaciones relacionadas a los métodos de pago")
@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService service;

    @Operation(summary = "Listar métodos de pago", description = "Devuelve todos los métodos de pago disponibles")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener método de pago por ID", description = "Devuelve el método de pago correspondiente al ID especificado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Método encontrado"),
        @ApiResponse(responseCode = "404", description = "Método no encontrado")
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear método de pago", description = "Crea un nuevo método de pago. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Método creado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<PaymentMethodDTO> create(@Valid @RequestBody PaymentMethodDTO dto) {
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @Operation(summary = "Actualizar método de pago", description = "Actualiza un método de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Método actualizado"),
        @ApiResponse(responseCode = "404", description = "Método no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> update(
            @PathVariable int id,
            @Valid @RequestBody PaymentMethodDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar método de pago", description = "Elimina un método de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Método eliminado"),
        @ApiResponse(responseCode = "404", description = "Método no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}