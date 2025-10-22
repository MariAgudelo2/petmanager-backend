package com.codefactory.petmanager.g12.petmanager_backend.user.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { 

    Optional<User> findByEmail(String email);

    Optional<User> findByIdNumberAndIdType(String idNumber, String idType);

    boolean existsByIdNumberAndIdType(String idNumber, String idType);

    boolean existsByEmail(String email);

    List<User> findByRoleName(String roleName);
    
    List<User> findAll();

}
