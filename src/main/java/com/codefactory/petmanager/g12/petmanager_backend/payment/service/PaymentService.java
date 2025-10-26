package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentsProductsRepository;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SupplierRepository supplierRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentsProductsRepository paymentProductsRepository;
    private final ProductMapper productMapper;

    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Supplier supplier = supplierRepository.findByNit(paymentRequestDTO.getSupplier().getNit())
            .orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado: " + paymentRequestDTO.getSupplier().getNit()));

        Payment payment = new Payment();
        payment.setSupplier(supplier);
        payment.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payment.setNotes(paymentRequestDTO.getNotes());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<PaymentsProducts> paymentProductsList = new ArrayList<>();

        for (var productRequestDTO : paymentRequestDTO.getProducts()) {
            Product product = productMapper.productDTOToProduct(productRequestDTO.getProduct());

            PaymentsProducts paymentProduct = new PaymentsProducts();
            paymentProduct.setPayment(payment);
            paymentProduct.setProduct(product);
            paymentProduct.setQuantity(productRequestDTO.getQuantity());
            paymentProduct.setPricePerUnit(productRequestDTO.getPricePerUnit());

            BigDecimal subtotal = productRequestDTO.getPricePerUnit()
                    .multiply(BigDecimal.valueOf(productRequestDTO.getQuantity()));
            totalAmount = totalAmount.add(subtotal);

            paymentProductsList.add(paymentProduct);
        }

        payment.setAmount(totalAmount);
        payment.setPaymentsProducts(paymentProductsList);

        Payment savedPayment = paymentRepository.save(payment);

        paymentProductsRepository.saveAll(paymentProductsList);

        return paymentMapper.paymentToPaymentResponseDTO(savedPayment);
    }

} 
    
