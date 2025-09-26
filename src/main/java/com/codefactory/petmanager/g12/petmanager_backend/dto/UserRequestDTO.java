package com.codefactory.petmanager.g12.petmanager_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
  
  @NotBlank(message = "ID number is required")
  private String idNumber;
  
  @NotBlank(message = "ID type is required")
  private String idType;
  
  @NotBlank(message = "Name is required")
  private String name;
  
  @NotNull(message = "Role ID is required")
  private int roleId;
  
  @NotBlank(message = "Phone number is required")
  private String phoneNumber;
  
  @Email(message = "Email must be valid")
  @NotBlank(message = "Email is required")
  private String email;
  
  @NotBlank(message = "Password is required")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String password;
  
  private boolean isActive = true;
  
}