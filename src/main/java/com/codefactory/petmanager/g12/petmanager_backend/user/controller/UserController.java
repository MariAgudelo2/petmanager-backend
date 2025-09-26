package com.codefactory.petmanager.g12.petmanager_backend.user.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.codefactory.petmanager.g12.petmanager_backend.common.exceptions.dto.ErrorResponse;
import com.codefactory.petmanager.g12.petmanager_backend.identity.controller.dto.RoleDTO;
import com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto.UserRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.user.controller.dto.UserResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuarios", description = "Operaciones relacionadas a los usuarios")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Listar usuarios", description = "Devuelve todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtener usuario por id", description = "Devuelve el usuario que coincida con el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable int id) {
        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @Operation(summary = "Crear usuario", description = "Crea un nuevo usuario con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Cambiar rol de usuario", description = "Actualiza el rol de un usuario que coincida con el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol actualizado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UserResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario o rol no encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/role/{roleId}")
    public ResponseEntity<UserResponseDTO> changeUserRole(@PathVariable int id, @PathVariable int roleId) {
        UserResponseDTO updatedUser = userService.changeUserRole(id, roleId);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Listar roles", description = "Devuelve todos los roles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = RoleDTO.class)))
    })
    @GetMapping("/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = userService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Operation(summary = "Obtener rol por ID", description = "Devuelve un rol que coincida con el ID proporcionado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rol encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = RoleDTO.class))),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/roles/{id}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable int id) {
        RoleDTO role = userService.getRoleById(id);
        return ResponseEntity.ok(role);
    }
}