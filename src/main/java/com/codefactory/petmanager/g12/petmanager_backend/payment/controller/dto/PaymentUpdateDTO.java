package com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentUpdateDTO {

    @NotNull(message = "El ID del pago es requerido")
    private Integer id;

    @NotNull(message = "El monto es requerido")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor que 0")
    private BigDecimal amount;

    private String notes;
}
