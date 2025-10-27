package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    
    private int paymentId;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private List<PaymentsProductsDTO> products;
    private String notes;

}