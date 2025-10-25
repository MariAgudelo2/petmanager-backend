package com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto;

import java.math.BigDecimal;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

import lombok.Data;

@Data
public class ContractProductResponseDTO {

    private int id;
    private int contractId;
    private Product product;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalAmount;
}
