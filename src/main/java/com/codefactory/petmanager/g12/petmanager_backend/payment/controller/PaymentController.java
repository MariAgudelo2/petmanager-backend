package com.codefactory.petmanager.g12.petmanager_backend.payment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionsResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.SupplierLastNextPaymentsResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.SupplierPaymentsResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
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

  @Operation(summary = "Obtener todas las condiciones de pago predefinidas", description = "Devuelve la lista de condiciones de pago que están predefinidas en el sistema")
  @ApiResponse(responseCode = "200", description = "Operación exitosa",
      content = @Content(mediaType = "application/json",
      schema = @Schema(implementation = PaymentConditionsResponseDTO.class)))
  @GetMapping("/conditions")
  public ResponseEntity<PaymentConditionsResponseDTO> getAllPaymentsConditions() {
    List<PaymentCondition> paymentConditions = paymentService.getAllPaymentConditions();
    PaymentConditionsResponseDTO responseDTO = new PaymentConditionsResponseDTO(paymentConditions);
    return ResponseEntity.ok(responseDTO);
  }

  @Operation(summary = "Obtener todos los registros de pagos de un proveedor", description = "Devuelve una lista de los registros de pago que tiene el proveedor dado su id")
  @ApiResponse(responseCode = "200", description = "Operación exitosa",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = SupplierPaymentsResponseDTO.class)))
  @GetMapping("/supplier/{supplierId}")
  public ResponseEntity<SupplierPaymentsResponseDTO> getPaymentsBySupplierId(@PathVariable int supplierId) {
    SupplierPaymentsResponseDTO response = paymentService.getAllPaymentsBySupplierId(supplierId);
    return ResponseEntity.ok(response);
  }

  @Operation(summary = "Obtener el último y siguiente pago programado de un proveedor", description = "Devuelve el último pago realizado a el proveedor y el pago siguiente que ha sido programado")
  @ApiResponse(responseCode = "200", description = "operación exitosa",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = SupplierLastNextPaymentsResponseDTO.class)))
  @GetMapping("/supplier/{supplierId}/last-and-next")
  public ResponseEntity<SupplierLastNextPaymentsResponseDTO> getLastAndNextPaymentBySupplierId(@PathVariable int supplierId) {
    SupplierLastNextPaymentsResponseDTO response = paymentService.getLastAndNextPaymentsBySupplierId(supplierId);
    return ResponseEntity.ok(response);
  }
}
