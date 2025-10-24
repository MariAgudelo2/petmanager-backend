package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentConditionRequestDTO {

    private Integer id; // null al crear, útil para updates

    @NotNull(message = "El ID del término de pago es requerido")
    private Integer paymentTermId;

    @NotNull(message = "El ID del método de pago es requerido")
    private Integer paymentMethodId;

    @NotBlank(message = "La descripción es requerida")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String description;
}
