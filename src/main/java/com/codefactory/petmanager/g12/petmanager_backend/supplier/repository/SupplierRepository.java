package com.codefactory.petmanager.g12.petmanager_backend.supplier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {

  Optional<Supplier> findByNit(String nit);

  Optional<Supplier> findByName(String name);

  List<Supplier> findAll();

  boolean existsByNit(String nit);

  List<Supplier> findByNameContainingIgnoreCase(String name);
  
  List<Supplier> findByNitContaining(String nit);
  
  List<Supplier> findByAddressContainingIgnoreCase(String address);

}