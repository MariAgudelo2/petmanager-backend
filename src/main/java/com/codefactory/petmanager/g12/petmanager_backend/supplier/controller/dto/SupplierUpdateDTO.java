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
    
  @Pattern(regexp = "^[0-9]{9,15}$", message = "El NIT debe contener entre 9 y 15 digitos")
  private String nit;
  
  @Size(min = 2, max = 255, message = "El nombre debe contener entre 2 y 255 carácteres")
  private String name;
  
  @Pattern(regexp = "^[0-9+\\-\\s()]{10,16}$", message = "El número de telefono debe contener entre 10 y 16 carácteres")
  private String phoneNumber;
  
  @Size(min = 10, max = 255, message = "La dirección debe contener entre 10 y 255 carácteres")
  private String address;
  
  @Email(message = "El email debe ser válido")
  private String email;

  private Integer paymentConditionId;

  private String paymentNotes;

}