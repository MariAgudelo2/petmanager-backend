package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import lombok.Data;

@Data
public class PaymentConditionResponseDTO {
    private Integer id;
    private String description;

    private PaymentMethodDTO paymentMethod;
    private PaymentTermDTO paymentTerm;
}
