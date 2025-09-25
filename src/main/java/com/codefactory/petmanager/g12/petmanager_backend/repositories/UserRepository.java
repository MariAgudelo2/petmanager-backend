package com.codefactory.petmanager.g12.petmanager_backend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codefactory.petmanager.g12.petmanager_backend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { 

    Optional<User> findByEmail(String email);

    Optional<User> findByIdNumberAndIdType(String idNumber, String idType);

    List<User> findByRoleName(String roleName);
    
    List<User> findAll();

}
