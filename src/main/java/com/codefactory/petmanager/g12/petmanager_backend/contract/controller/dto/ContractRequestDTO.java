package com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto;

import java.time.LocalDate;

import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequestDTO {

  @NotNull(message = "El ID del proveedor es obligatorio")
  private Integer supplierId;

  @NotNull(message = "El ID de la condición de pago es obligatorio")
  private Integer paymentConditionId;

  @NotNull(message = "La fecha de inicio del contrato es obligatoria")
  private LocalDate contractStartDate;

  @NotNull(message = "La fecha de finalización del contrato es obligatoria")
  private LocalDate contractEndDate;

  @NotNull(message = "El estado del contrato es obligatorio")
  private ContractStatus status;
}
