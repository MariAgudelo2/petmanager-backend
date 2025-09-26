package com.codefactory.petmanager.g12.petmanager_backend.contract.model;

import java.time.LocalDate;

import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

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
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "payment_condition_id", nullable = false)
  private PaymentCondition paymentCondition;

  @Column(name = "contract_start_date", nullable = false)
  private LocalDate contractStartDate;

  @Column(name = "contract_end_date", nullable = false)
  private LocalDate contractEndDate;

  @Column(name = "status", nullable = false, length = 255)
  private String status;
  
}

