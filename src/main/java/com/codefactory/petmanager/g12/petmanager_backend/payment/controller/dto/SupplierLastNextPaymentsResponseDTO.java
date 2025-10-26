package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierLastNextPaymentsResponseDTO {

  private int supplierId;
  private PaymentResponseDTO next;
  private PaymentResponseDTO last;
  
}
