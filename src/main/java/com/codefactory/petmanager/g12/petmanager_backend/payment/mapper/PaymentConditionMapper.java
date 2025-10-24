package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentConditionResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentMethod;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentTerm;

@Mapper(componentModel = "spring")
public interface PaymentConditionMapper {

    @Mapping(source = "paymentTerm.id", target = "paymentTermId")
    @Mapping(source = "paymentMethod.id", target = "paymentMethodId")
    PaymentConditionRequestDTO toRequestDTO(PaymentCondition condition);

    @Mapping(source = "paymentTerm", target = "paymentTerm")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    PaymentConditionResponseDTO toResponseDTO(PaymentCondition condition);

    List<PaymentConditionResponseDTO> toResponseDTOList(List<PaymentCondition> conditions);

    @Mapping(target = "paymentTerm", expression = "java(paymentTermFromId(dto.getPaymentTermId()))")
    @Mapping(target = "paymentMethod", expression = "java(paymentMethodFromId(dto.getPaymentMethodId()))")
    @Mapping(target = "id", ignore = true)
    PaymentCondition toEntity(PaymentConditionRequestDTO dto);

    default PaymentTerm paymentTermFromId(Integer id) {
        if (id == null) return null;
        PaymentTerm term = new PaymentTerm();
        term.setId(id);
        return term;
    }

    default PaymentMethod paymentMethodFromId(Integer id) {
        if (id == null) return null;
        PaymentMethod method = new PaymentMethod();
        method.setId(id);
        return method;
    }
}
