package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentsProductsDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PaymentsProductsMapper {
    
    @Mapping(source = "product", target = "product")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "pricePerUnit", target = "pricePerUnit")
    PaymentsProductsDTO paymentsProductsToPaymentsProductsDTO(PaymentsProducts paymentsProducts);
    
    List<PaymentsProductsDTO> paymentsProductsListToPaymentsProductsDTOList(List<PaymentsProducts> paymentsProductsList);
}