package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Product;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateDTO {

    @FutureOrPresent(message = "La fecha no puede ser anterior a la actual")
    private LocalDate paymentDate;

    private Product product;

    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private int quantity;

    @Min(value = 0, message = "El precio por unidad debe ser mayor o igual a 0")
    private BigDecimal pricePerUnit;

    private String notes;
}

