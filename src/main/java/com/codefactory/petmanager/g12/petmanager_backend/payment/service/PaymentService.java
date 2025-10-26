package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentConditionRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentsProductsRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final PaymentsProductsRepository paymentProductsRepository;
    private final PaymentConditionRepository paymentConditionRepository;
    private final PaymentMapper paymentMapper;
    private final ProductMapper productMapper;

    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Supplier supplier = supplierRepository.findById(paymentRequestDTO.getSupplierId())
                        .orElseThrow(() -> new IllegalArgumentException("No existe un proveedor con id: " + paymentRequestDTO.getSupplierId()));

        Payment payment = new Payment();
        payment.setSupplier(supplier);
        payment.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payment.setNotes(paymentRequestDTO.getNotes());

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (var productDTO : paymentRequestDTO.getProducts()) {
            BigDecimal subtotal = productDTO.getPricePerUnit()
                            .multiply(BigDecimal.valueOf(productDTO.getQuantity()));
            totalAmount = totalAmount.add(subtotal);
        }
        payment.setAmount(totalAmount);
        Payment savedPayment = paymentRepository.save(payment);

        List<PaymentsProducts> paymentProductsList = new ArrayList<>();

        for (var productDTO : paymentRequestDTO.getProducts()) {
            Product mappedProduct = productMapper.productDTOToProduct(productDTO.getProduct());
            Product savedProduct = null;
            // Comprobar si el producto ya existe en la bd o crear uno nuevo
            if (productRepository.existsByNameIgnoreCaseAndBrandIgnoreCase(mappedProduct.getName(), mappedProduct.getBrand())) {
                savedProduct = productRepository.findByNameIgnoreCaseAndBrandIgnoreCase(mappedProduct.getName(), mappedProduct.getBrand());
            } else {
                savedProduct = productRepository.save(mappedProduct);
            }

            PaymentsProducts paymentProduct = new PaymentsProducts();
            paymentProduct.setPayment(savedPayment);
            paymentProduct.setProduct(savedProduct);
            paymentProduct.setQuantity(productDTO.getQuantity());
            paymentProduct.setPricePerUnit(productDTO.getPricePerUnit());
            paymentProduct.setTotalAmount(productDTO.getPricePerUnit()
                            .multiply(BigDecimal.valueOf(productDTO.getQuantity())));
            paymentProductsList.add(paymentProduct);
        }

        paymentProductsRepository.saveAll(paymentProductsList);

        return paymentMapper.paymentToPaymentResponseDTO(savedPayment);
    }

    @Transactional(readOnly = true)
    public List<PaymentCondition> getAllPaymentConditions() {
        List<PaymentCondition> paymentConditions = paymentConditionRepository.findAll();
        return paymentConditions;
    }
}