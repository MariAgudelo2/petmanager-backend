package com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContractProductRequestDTO {

    @NotNull(message = "El ID del contrato es obligatorio")
    private Integer contractId;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productId;

    @Min(value = 1, message = "La cantidad debe ser mayor que cero")
    private int quantity;

    @NotNull(message = "El precio por unidad es obligatorio")
    private BigDecimal pricePerUnit;

    @NotNull(message = "El monto total es obligatorio")
    private BigDecimal totalAmount;
}