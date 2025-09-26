package com.codefactory.petmanager.g12.petmanager_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    
  private int id;
  private String idNumber;
  private String idType;
  private String name;
  private RoleDTO role;
  private String phoneNumber;
  private String email;
  private boolean isActive;
    
}