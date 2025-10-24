package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentTermDTO {

  private int id; // Ignorado en POST

  @NotBlank(message = "El nombre del término de pago es requerido")
  @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
  private String name;

  @NotNull(message = "El número de días es requerido")
  @Min(value = 0, message = "El número de días debe ser mayor o igual a 0")
  private Integer days;

  @NotBlank(message = "La descripción es requerida")
  @Size(max = 255, message = "La descripción no debe exceder 255 caracteres")
  private String description;
}
