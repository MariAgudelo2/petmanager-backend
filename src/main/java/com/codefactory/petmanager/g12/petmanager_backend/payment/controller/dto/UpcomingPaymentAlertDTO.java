package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpcomingPaymentAlertDTO {
    private int paymentId;
    private int supplierId;
    private String supplierName;
    private LocalDate paymentDate;
    private long daysUntilPayment;
    private BigDecimal amount;
    private List<ProductDTO> providedProducts;
    private PaymentCondition paymentCondition;
}
