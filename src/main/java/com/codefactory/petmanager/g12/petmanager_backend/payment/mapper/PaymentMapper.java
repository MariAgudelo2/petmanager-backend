package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  PaymentResponseDTO paymentToPaymentResponseDTO(Payment payment);
  //Payment paymentRequestDTOToPayment(PaymentRequestDTO paymentRequestDTO);
  //Payment paymentUpdateDTOToPayment(PaymentUpdateDTO paymentUpdateDTO);
  List<PaymentResponseDTO> paymentsToPaymentResponseDTOs(List<Payment> payments);

}
