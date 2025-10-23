package com.codefactory.petmanager.g12.petmanager_backend.supplier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "nit", nullable = false, length = 255, unique = true)
  private String nit;

  @Column(name = "name", nullable = false, length = 255, unique = true)
  private String name;

  @Column(name = "phone_number", nullable = false, length = 16)
  private String phoneNumber;

  @Column(name = "address", nullable = false, length = 255)
  private String address;
  
  @Column(name = "email", nullable = true, length = 255)
  private String email;
  
}
