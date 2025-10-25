package com.codefactory.petmanager.g12.petmanager_backend.contract.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.Contract;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "paymentCondition", ignore = true)
    Contract toEntity(ContractRequestDTO dto);

    ContractResponseDTO toResponseDTO(Contract contract);

    List<ContractResponseDTO> toResponseDTOList(List<Contract> contracts);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "paymentCondition", ignore = true)
    void updateEntityFromDTO(ContractRequestDTO dto, @MappingTarget Contract entity);

    @AfterMapping
    default void setRelations(ContractRequestDTO dto, @MappingTarget Contract entity) {
        if (dto.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setId(dto.getSupplierId());
            entity.setSupplier(supplier);
        }

        if (dto.getPaymentConditionId() != null) {
            PaymentCondition paymentCondition = new PaymentCondition();
            paymentCondition.setId(dto.getPaymentConditionId());
            entity.setPaymentCondition(paymentCondition);
        }
    }
}
