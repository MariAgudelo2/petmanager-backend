package com.codefactory.petmanager.g12.petmanager_backend.contract.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractProduct;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractProductMapper {

    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "id", ignore = true)
    ContractProduct toEntity(ContractProductRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateEntityFromDTO(ContractProductRequestDTO dto, @MappingTarget ContractProduct entity);

    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ContractProductResponseDTO toResponseDTO(ContractProduct entity);

    List<ContractProductResponseDTO> toResponseDTOList(List<ContractProduct> entities);

}