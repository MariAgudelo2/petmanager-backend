package com.codefactory.petmanager.g12.petmanager_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    Role findByName(String name);
}
