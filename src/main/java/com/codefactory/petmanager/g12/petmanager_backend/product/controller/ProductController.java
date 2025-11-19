package com.codefactory.petmanager.g12.petmanager_backend.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto.SupplierProductsResponse;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.SuppliersProducts;
import com.codefactory.petmanager.g12.petmanager_backend.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para gestionar productos y su relación con proveedores")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Obtener todos los productos", description = "Retorna una lista con todos los productos disponibles en el sistema")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
        content = @Content(mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(implementation = Product.class))))
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }
    
    @Operation(summary = "Obtener productos por proveedor", description = "Retorna todos los productos asociados a un proveedor específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos del proveedor obtenidos exitosamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = SupplierProductsResponse.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<SupplierProductsResponse> getProductsBySupplier(@PathVariable int supplierId) {
        var response = productService.findProductsBySupplierId(supplierId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Añadir producto a proveedor", description = "Crea una nueva relación entre un proveedor y un producto. Se requiere rol MANAGER o ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Relación proveedor-producto creada exitosamente",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = SuppliersProducts.class))),
        @ApiResponse(responseCode = "404", description = "Proveedor o producto no encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/supplier-product")
    public ResponseEntity<SuppliersProducts> addSupplierProduct(@RequestParam int supplierId, @RequestParam int productId) {
        SuppliersProducts supplierProduct = productService.addSupplierProduct(supplierId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierProduct);
    }

    @Operation(summary = "Eliminar relación proveedor-producto", description = "Elimina la relación entre un proveedor y un producto. Se requiere rol MANAGER o ADMIN")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Relación eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Relación proveedor-producto no encontrada")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/supplier-product/{supplierProductId}")
    public ResponseEntity<Void> deleteSupplierProduct(@PathVariable int supplierProductId) {
        productService.deleteSupplierProduct(supplierProductId);
        return ResponseEntity.noContent().build();
    }

}
