package com.codefactory.petmanager.g12.petmanager_backend.entities;

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
@Table(name = "payment_terms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTerm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "days", nullable = false)
  private int days;

  @Column(name = "description", nullable = false, length = 255)
  private String description;
  
}
