package com.codefactory.petmanager.g12.petmanager_backend.product.model;

import java.math.BigDecimal;

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
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "brand", nullable = false, length = 255)
  private String brand;

  @Column(name = "sale_price", nullable = false)
  private BigDecimal salePrice;

  @Column(name = "description", length = 255)
  private String description;
  
}

