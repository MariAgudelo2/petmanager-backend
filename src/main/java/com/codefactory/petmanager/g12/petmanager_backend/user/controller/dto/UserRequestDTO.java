package com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
  
  @NotBlank(message = "ID number is required")
  @Pattern(regexp = "^[0-9]{6,12}$", message = "Document must be between 6 and 12 digits and contain only numbers")
  private String idNumber;
  
  @NotBlank(message = "ID type is required")
  private String idType;
  
  @NotBlank(message = "Name is required")
  @Pattern(regexp = "^(?=.*\\s)([A-Za-záéíóúÁÉÍÓÚñÑ\\s]{5,100})$", message = "Name must have at least two words, between 5 and 100 characters, and contain only letters and spaces")
  private String name;
  
  @NotNull(message = "Role ID is required")
  private int roleId;
  
  @NotBlank(message = "Phone number is required")
  private String phoneNumber;
  
  @Email(message = "Email must be valid")
  @NotBlank(message = "Email is required")
  private String email;
  
  @NotBlank(message = "Password is required")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
           message = "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one number, and one special character (@, $, %, !, #, ?)")
  private String password;
  
  private boolean isActive = true;
  
}