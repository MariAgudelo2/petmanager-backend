package com.codefactory.petmanager.g12.petmanager_backend.contract.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.service.ContractService;
import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Contratos", description = "Operaciones relacionadas con los contratos")
@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService service;

    @Operation(summary = "Listar todos los contratos")
    @ApiResponse(responseCode = "200", content = @Content(
        schema = @Schema(implementation = ContractResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<ContractResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener contrato por ID")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponseDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear nuevo contrato")
    @ApiResponse(responseCode = "201", content = @Content(
        schema = @Schema(implementation = ContractResponseDTO.class)))
    @PostMapping
    public ResponseEntity<ContractResponseDTO> create(@Valid @RequestBody ContractRequestDTO dto) {
        ContractResponseDTO created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Actualizar contrato existente")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<ContractResponseDTO> update(
        @PathVariable int id,
        @Valid @RequestBody ContractRequestDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar contrato")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
