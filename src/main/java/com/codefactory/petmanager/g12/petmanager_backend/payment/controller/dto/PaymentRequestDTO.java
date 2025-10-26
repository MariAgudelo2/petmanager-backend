package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.time.LocalDate;
import java.util.List;

import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private Supplier supplier;

    @FutureOrPresent(message = "La fecha no puede ser anterior a la actual")
    private LocalDate paymentDate;

    private List<PaymentsProductsDTO> products;

    private String notes;
}

