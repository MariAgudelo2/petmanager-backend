package com.codefactory.petmanager.g12.petmanager_backend.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "contract_product_id", nullable = false)
  private ContractProduct contractProduct;

  @Column(name = "delivery_date", nullable = false)
  private LocalDate deliveryDate;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "received_by", nullable = false)
  private User user;

  @Column(name = "notes", length = 255)
  private String notes;

}
