package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PaymentMethodDTO {

    private Integer id; // Null cuando se crea

    @NotBlank(message = "El nombre del método de pago es requerido")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no debe exceder 255 caracteres")
    private String description;
}

