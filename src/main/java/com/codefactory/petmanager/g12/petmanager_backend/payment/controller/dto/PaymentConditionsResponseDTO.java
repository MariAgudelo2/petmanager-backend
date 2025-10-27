package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.util.List;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConditionsResponseDTO {
  List<PaymentCondition> paymentConditions;
}
