package com.codefactory.petmanager.g12.petmanager_backend.auth.controller.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}


