package com.codefactory.petmanager.g12.petmanager_backend.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.codefactory.petmanager.g12.petmanager_backend.auth.controller.dto.AuthRequest;
import com.codefactory.petmanager.g12.petmanager_backend.auth.controller.dto.AuthResponse;
import com.codefactory.petmanager.g12.petmanager_backend.auth.service.AuthService;
import com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto.UserRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto.UserResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación y registro", description = "Endpoints para la autenticación y registro de usuarios")
public class AuthController {

    private final AuthService authService;
	private final UserService userService;

	@Operation(summary = "Inicio de sesión", description = "Autentica el usuario y devuelve un access token JWT.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso, token devuelto"),
		@ApiResponse(responseCode = "401", description = "Credenciales invalidas")
	})
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RequestBody AuthRequest req) {
		String token = authService.login(req.getEmail(), req.getPassword());
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@Operation(summary = "Registro de usuarios", description = "Registra un nuevo usuario y devuelve la información registrada.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
		@ApiResponse(responseCode = "400", description = "Información de registro inválida")
	})
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> register(
			@Valid @RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO createdUser = userService.createUser(userRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
}


