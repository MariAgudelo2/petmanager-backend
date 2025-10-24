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

    @Operation(summary = "Listar todos los términos de pago", description = "Devuelve todos los términos de pago disponibles.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentTermDTO.class)))
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<PaymentTermDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener término de pago por ID", description = "Devuelve el término de pago correspondiente al ID especificado.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Término encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentTermDTO.class))),
        @ApiResponse(responseCode = "404", description = "Término no encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentTermDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear nuevo término de pago", description = "Crea un nuevo término de pago. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Término creado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentTermDTO.class))),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<PaymentTermDTO> create(@Valid @RequestBody PaymentTermDTO dto) {
        PaymentTermDTO created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Actualizar término de pago existente", description = "Actualiza un término de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Término actualizado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = PaymentTermDTO.class))),
        @ApiResponse(responseCode = "404", description = "Término no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentTermDTO> update(
        @PathVariable int id,
        @Valid @RequestBody PaymentTermDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar término de pago", description = "Elimina un término de pago existente. Requiere rol ADMIN o MANAGER.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Término eliminado"),
        @ApiResponse(responseCode = "404", description = "Término no encontrado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}