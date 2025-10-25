package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private Supplier supplier;

    @NotBlank(message = "La fecha de pago es requerida")
    @FutureOrPresent(message = "La fecha no puede ser anterior a la actual")
    private LocalDate paymentDate;

    private BigDecimal amount;

    private String notes;
}

