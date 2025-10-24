package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentMethodDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentMethod;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethod toEntity(PaymentMethodDTO dto);

    PaymentMethodDTO toDTO(PaymentMethod entity);

    List<PaymentMethodDTO> toDTOList(List<PaymentMethod> list);

    void updateEntityFromDTO(PaymentMethodDTO dto, @MappingTarget PaymentMethod entity);
}