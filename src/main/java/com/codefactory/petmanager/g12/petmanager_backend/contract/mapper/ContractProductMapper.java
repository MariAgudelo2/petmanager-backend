package com.codefactory.petmanager.g12.petmanager_backend.contract.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.controller.dto.ContractProductRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractProduct;

@Mapper(componentModel = "spring")
public interface ContractProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contract", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "pricePerUnit", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    ContractProduct toEntity(ContractProductRequestDTO dto);

    @Mapping(target = "contractId", source = "contract.id")
    ContractProductResponseDTO toResponseDTO(ContractProduct entity);

    List<ContractProductResponseDTO> toResponseDTOList(List<ContractProduct> entities);
}