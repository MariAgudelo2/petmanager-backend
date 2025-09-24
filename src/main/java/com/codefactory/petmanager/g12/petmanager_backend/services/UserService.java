package com.codefactory.petmanager.g12.petmanager_backend.services;

import org.springframework.stereotype.Service;

import com.codefactory.petmanager.g12.petmanager_backend.dto.UserDTO;
import com.codefactory.petmanager.g12.petmanager_backend.entities.Role;
import com.codefactory.petmanager.g12.petmanager_backend.entities.User;
import com.codefactory.petmanager.g12.petmanager_backend.repositories.RoleRepository;
import com.codefactory.petmanager.g12.petmanager_backend.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDTO getUserByEmail (String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return convertToDTO(user);
    }
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid role ID: " + userDTO.getRoleId()));

        user.setIdNumber(userDTO.getIdNumber());
        user.setIdType(userDTO.getIdType());
        user.setName(userDTO.getName());
        user.setRole(role);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setIdNumber(user.getIdNumber());
        userDTO.setIdType(user.getIdType());
        userDTO.setName(user.getName());
        userDTO.setRoleId(user.getRole().getId());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }
}
