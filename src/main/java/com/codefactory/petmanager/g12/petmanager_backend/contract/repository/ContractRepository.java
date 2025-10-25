package com.codefactory.petmanager.g12.petmanager_backend.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codefactory.petmanager.g12.petmanager_backend.contract.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
