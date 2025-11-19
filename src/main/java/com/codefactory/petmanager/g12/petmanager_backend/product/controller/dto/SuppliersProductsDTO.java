package com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuppliersProductsDTO {
    
    private int supplierProductId;
    private Product product;

}
