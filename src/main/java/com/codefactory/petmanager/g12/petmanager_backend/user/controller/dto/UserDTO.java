package com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    
    private String idNumber;
    private String idType;
    private String name;
    private int roleId;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean isActive;
    
}
