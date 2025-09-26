package com.codefactory.petmanager.g12.petmanager_backend.supplier.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierUpdateDTO {
    
  @Pattern(regexp = "^[0-9]{9,15}$", message = "NIT must be between 9 and 15 digits")
  private String nit;
  
  @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
  private String name;
  
  @Pattern(regexp = "^[0-9+\\-\\s()]{10,16}$", message = "Phone number must be between 10 and 16 characters")
  private String phoneNumber;
  
  @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
  private String address;
  
  @Email(message = "Email must be valid")
  private String email;

}