package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "id", target = "paymentId")
    @Mapping(source = "paymentDate", target = "paymentDate")
    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "notes", target = "notes")
    @Mapping(target = "products", ignore = true)
    PaymentResponseDTO paymentToPaymentResponseDTO(Payment payment);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "paymentsProducts", ignore = true)
    Payment paymentRequestDTOToPayment(PaymentRequestDTO paymentRequestDTO);
    
    List<PaymentResponseDTO> paymentsToPaymentResponseDTOs(List<Payment> payments);
}