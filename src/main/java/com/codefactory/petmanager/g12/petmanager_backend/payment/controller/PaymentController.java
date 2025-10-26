package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Tag(name = "Pagos", description = "Operaciones CRUD relacionadas a los pagos")
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Crear pago", description = "Programar un nuevo pago con los datos proporcionados. Necesario rol ADMIN o MANAGER.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Pago programado",
        content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = PaymentResponseDTO.class))),
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
  @PostMapping
  public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO) {
    PaymentResponseDTO createdPayment = paymentService.createPayment(paymentRequestDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
  }
}
