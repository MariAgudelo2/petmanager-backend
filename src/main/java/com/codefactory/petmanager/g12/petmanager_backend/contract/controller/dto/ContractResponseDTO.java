package com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto;

import java.time.LocalDate;

import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractStatus;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierResponseDTO;

import lombok.Data;

@Data
public class ContractResponseDTO {

    private int id;
    private SupplierResponseDTO supplier;
    private PaymentConditionResponseDTO paymentCondition;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private ContractStatus status;
}
