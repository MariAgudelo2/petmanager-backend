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
  
  @NotBlank(message = "El documento de identificación es requerido")
  @Pattern(regexp = "^[0-9]{6,12}$", message = "El documento de identificación debe contener entre 6 y 12 digitos")
  private String idNumber;
  
  @NotBlank(message = "El tipo de documento es requerido")
  private String idType;
  
  @NotBlank(message = "El nombre es requerido")
  @Pattern(regexp = "^(?=.*\\s)([A-Za-záéíóúÁÉÍÓÚñÑ\\s]{5,100})$", message = "El nombre debe contener al menos dos palabras, entre 5 y 100 carácteres, y contener solo letras y espacios")
  private String name;
  
  @NotNull(message = "Role ID es requerido")
  private int roleId;
  
  @NotBlank(message = "El número de telefono es requerido")
  private String phoneNumber;
  
  @Email(message = "El email debe ser válido")
  @NotBlank(message = "El email es requerido")
  private String email;
  
  @NotBlank(message = "La contraseña es requerida")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
           message = "La contraseña debe contener al menos 8 carácteres, incluyendo una letra mayúscula, una letra minúscula, un número, y un carácter especial (@, $, %, !, #, ?)")
  private String password;
  
  private boolean isActive = true;
  
}