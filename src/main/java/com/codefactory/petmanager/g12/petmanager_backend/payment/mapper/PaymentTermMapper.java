package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;
import org.mapstruct.*;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentTermDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentTerm;

@Mapper(componentModel = "spring")
public interface PaymentTermMapper {

  PaymentTermDTO toDTO(PaymentTerm entity);

  @Mapping(target = "id", ignore = true)
  PaymentTerm toEntity(PaymentTermDTO dto);

  @Mapping(target = "id", ignore = true)
  void updateEntityFromDTO(PaymentTermDTO dto, @MappingTarget PaymentTerm entity);

  List<PaymentTermDTO> toDTOList(List<PaymentTerm> entities);
}
