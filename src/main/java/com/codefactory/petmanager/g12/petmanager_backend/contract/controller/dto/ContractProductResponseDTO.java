package com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ContractProductResponseDTO {

    private int id;
    private int contractId;
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalAmount;
}
