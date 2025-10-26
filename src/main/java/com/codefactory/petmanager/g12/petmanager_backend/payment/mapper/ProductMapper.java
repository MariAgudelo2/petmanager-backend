package com.codefactory.petmanager.g12.petmanager_backend.payment.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    
  ProductDTO productToProductDTO(Product product);
  Product productDTOToProduct(ProductDTO productDTO);
  List<ProductDTO> productsToProductDTOs(List<Product> products);
  List<Product> productDTOsToProducts(List<ProductDTO> productDTOs);

}
