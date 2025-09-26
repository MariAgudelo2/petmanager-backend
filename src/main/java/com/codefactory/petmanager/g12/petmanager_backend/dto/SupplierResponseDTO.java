package com.codefactory.petmanager.g12.petmanager_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDTO {
    
  private int id;
  private String nit;
  private String name;
  private String phoneNumber;
  private String address;

}