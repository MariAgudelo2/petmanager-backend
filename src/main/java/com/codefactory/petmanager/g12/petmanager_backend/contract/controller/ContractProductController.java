package com.codefactory.petmanager.g12.petmanager_backend.contract.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.service.ContractProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(
    name = "Productos de contrato",
    description = "Operaciones para gestionar los productos asociados a un contrato"
)
@RestController
@RequestMapping("/api/contract-products")
@RequiredArgsConstructor
public class ContractProductController {

    private final ContractProductService service;

    @Operation(
        summary = "Listar todos los productos de contrato",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de productos de contrato obtenida correctamente",
                content = @Content(schema = @Schema(implementation = ContractProductResponseDTO.class))
            )
        }
    )
    @GetMapping
    public ResponseEntity<List<ContractProductResponseDTO>> getAll() {
        List<ContractProductResponseDTO> products = service.getAll();
        return ResponseEntity.ok(products);
    }

    @Operation(
        summary = "Obtener un producto de contrato por ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Producto de contrato encontrado",
                content = @Content(schema = @Schema(implementation = ContractProductResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Producto de contrato no encontrado")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ContractProductResponseDTO> getById(@PathVariable int id) {
        ContractProductResponseDTO dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
        summary = "Crear un producto de contrato",
        responses = {
            @ApiResponse(responseCode = "201", description = "Producto de contrato creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        }
    )
    @PostMapping
    public ResponseEntity<ContractProductResponseDTO> create(@Valid @RequestBody ContractProductRequestDTO dto) {
        ContractProductResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(
        summary = "Actualizar un producto de contrato existente",
        responses = {
            @ApiResponse(responseCode = "200", description = "Producto de contrato actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto de contrato no encontrado")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ContractProductResponseDTO> update(
            @PathVariable int id,
            @Valid @RequestBody ContractProductRequestDTO dto) {
        ContractProductResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(
        summary = "Eliminar un producto de contrato",
        responses = {
            @ApiResponse(responseCode = "204", description = "Producto de contrato eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto de contrato no encontrado")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}