package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsProductsDTO {

    private ProductDTO product;
    private int quantity;
    private BigDecimal pricePerUnit;

}
