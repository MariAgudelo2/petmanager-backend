package com.codefactory.petmanager.g12.petmanager_backend.supplier.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto.SupplierResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

  SupplierResponseDTO supplierToSupplierResponseDTO(Supplier supplier);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "paymentCondition", ignore = true)
  Supplier supplierRequestDTOToSupplier(SupplierRequestDTO supplierRequestDTO);

  List<SupplierResponseDTO> suppliersToSupplierResponseDTOs(List<Supplier> suppliers);

}