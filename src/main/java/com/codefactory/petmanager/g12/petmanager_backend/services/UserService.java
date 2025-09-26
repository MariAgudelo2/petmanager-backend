package com.codefactory.petmanager.g12.petmanager_backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.dto.RoleDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.UserRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.dto.UserResponseDTO;
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

    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        return userMapper.userToUserResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        return userMapper.userToUserResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserResponseDTOs(users);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (!isRoleValid(userRequestDTO.getRoleId())) {
            throw new IllegalArgumentException("Invalid role id: " + userRequestDTO.getRoleId());
        }
        User user = userMapper.userRequestDTOToUser(userRequestDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserResponseDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO changeUserRole(int userId, int roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role id: " + roleId));
        user.setRole(role);
        User updatedUser = userRepository.save(user);
        return userMapper.userToUserResponseDTO(updatedUser);
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return userMapper.rolesToRoleDTOs(roles);
    }

    @Transactional(readOnly = true)
    public RoleDTO getRoleById(int roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + roleId));
        return userMapper.roleToRoleDTO(role);
    }

    private boolean isRoleValid(int roleId) {
        return roleRepository.existsById(roleId);
    }
}