package com.codefactory.petmanager.g12.petmanager_backend.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.product.service.ProductService;
import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;

import jakarta.validation.Valid;

@Tag(name = "Productos", description = "Operaciones relacionadas a los productos")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", content = @Content(
        schema = @Schema(implementation = ProductDTO.class)))
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener producto por ID")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear nuevo producto")
    @ApiResponse(responseCode = "201", content = @Content(
        schema = @Schema(implementation = ProductDTO.class)))
    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        ProductDTO created = service.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Actualizar producto existente")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(
        @PathVariable int id,
        @Valid @RequestBody ProductDTO dto
    ) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar producto")
    @ApiResponse(responseCode = "404", content = @Content(
        schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}