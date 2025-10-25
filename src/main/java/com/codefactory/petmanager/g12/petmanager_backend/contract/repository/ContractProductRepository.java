package com.codefactory.petmanager.g12.petmanager_backend.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.contract.model.ContractProduct;

@Repository
public interface ContractProductRepository extends JpaRepository<ContractProduct, Integer> {
}
