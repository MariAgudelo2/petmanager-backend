package com.codefactory.petmanager.g12.petmanager_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codefactory.petmanager.g12.petmanager_backend.dto.UserDTO;
import com.codefactory.petmanager.g12.petmanager_backend.entities.Role;
import com.codefactory.petmanager.g12.petmanager_backend.entities.User;
import com.codefactory.petmanager.g12.petmanager_backend.mapper.UserMapper;
import com.codefactory.petmanager.g12.petmanager_backend.repositories.RoleRepository;
import com.codefactory.petmanager.g12.petmanager_backend.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDTO getUserByEmail (String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return userMapper.userToUserDTO(user);
    }

    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDTOs(users);
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (!isRoleValid(userDTO.getRoleId())) throw new IllegalArgumentException("Invalid role id: " + userDTO.getRoleId());
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    public UserDTO changeUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role id: " + roleId));
        user.setRole(role);
        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDTO(updatedUser);
    }

    private boolean isRoleValid(int roleId) {
        return roleRepository.existsById(roleId);
    }
}
