package com.codefactory.petmanager.g12.petmanager_backend.auth.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    
    private int id;
    private String name;
    private String description;
    
}