package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Integer id;
    private Integer contractId;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private String notes;
}