package com.codefactory.petmanager.g12.petmanager_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codefactory.petmanager.g12.petmanager_backend.entities.Supplier;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

  Optional<Supplier> findByNit(String nit);

  Optional<Supplier> findByName(String name);

  List<Supplier> findAll();

  boolean existsByNit(String nit);

}
