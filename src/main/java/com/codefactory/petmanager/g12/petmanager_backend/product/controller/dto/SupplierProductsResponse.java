package com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductsResponse {
    
    private int supplierId;
    private String supplierName;
    private List<SuppliersProductsDTO> supplierProducts;

}
