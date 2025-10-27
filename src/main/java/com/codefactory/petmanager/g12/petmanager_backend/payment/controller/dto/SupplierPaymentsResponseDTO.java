package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPaymentsResponseDTO {
  private int supplierId;
  private List<PaymentResponseDTO> payments;
}
