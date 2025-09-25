package com.codefactory.petmanager.g12.petmanager_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codefactory.petmanager.g12.petmanager_backend.dto.UserDTO;
import com.codefactory.petmanager.g12.petmanager_backend.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(source = "role.id", target = "roleId")
  UserDTO userToUserDTO(User user);

  @Mapping(source = "roleId", target = "role.id")
  @Mapping(target = "id", ignore = true) // Autogenerado
  User userDTOToUser(UserDTO userDTO);
}
