package com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    private int id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String name;

    @NotBlank(message = "La marca del producto es obligatoria")
    private String brand;

    @NotNull(message = "El precio de venta es obligatorio")
    private BigDecimal salePrice;

    private String description;
}