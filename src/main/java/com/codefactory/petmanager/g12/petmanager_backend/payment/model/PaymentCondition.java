package com.codefactory.petmanager.g12.petmanager_backend.payment.model;

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
@Table(name = "payment_conditions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCondition {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "term_id", nullable = false)
  private PaymentTerm paymentTerm;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "method_id", nullable = false)
  private PaymentMethod paymentMethod;

  @Column(name = "description", nullable = false, length = 255)
  private String description;
  
}

