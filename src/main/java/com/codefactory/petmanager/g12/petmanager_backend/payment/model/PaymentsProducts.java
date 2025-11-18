package com.codefactory.petmanager.g12.petmanager_backend.payment.model;

import java.math.BigDecimal;

import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;

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
@Table(name = "payments_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentsProducts {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "payment_id", nullable = false)
  private Payment payment;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "price_per_unit", nullable = false)
  private BigDecimal pricePerUnit;
  
  @Column(name = "total_amount", nullable = false)
  private BigDecimal totalAmount;
}
