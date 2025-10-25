package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentUpdateDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "contract.id", target = "contractId")
    PaymentResponseDTO paymentToPaymentResponseDTO(Payment payment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "contract", expression = "java(new Contract(paymentRequestDTO.getContractId()))")
    Payment paymentRequestDTOToPayment(PaymentRequestDTO paymentRequestDTO);

    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    Payment paymentUpdateDTOToPayment(PaymentUpdateDTO paymentUpdateDTO);

    List<PaymentResponseDTO> paymentsToPaymentResponseDTOs(List<Payment> payments);
}