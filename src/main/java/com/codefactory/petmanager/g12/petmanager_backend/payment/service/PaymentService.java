package com.codefactory.petmanager.g12.petmanager_backend.payment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentRequestDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.PaymentsProductsDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.SupplierLastNextPaymentsResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.SupplierPaymentsResponseDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.UpcomingPaymentAlertDTO;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.mapper.PaymentsProductsMapper;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.Payment;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentCondition;
import com.codefactory.petmanager.g12.petmanager_backend.payment.model.PaymentsProducts;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentConditionRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentRepository;
import com.codefactory.petmanager.g12.petmanager_backend.payment.repository.PaymentsProductsRepository;
import com.codefactory.petmanager.g12.petmanager_backend.product.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.product.service.ProductService;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SupplierRepository supplierRepository;
    private final PaymentsProductsRepository paymentsProductsRepository;
    private final PaymentConditionRepository paymentConditionRepository;
    private final PaymentMapper paymentMapper;
    private final ProductMapper productMapper;
    private final PaymentsProductsMapper paymentsProductsMapper;
    private final ProductService productService;

    private final long DAYS_TO_ALERT = 10;

    @Transactional
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Supplier supplier = getSupplierOrThrow(paymentRequestDTO.getSupplierId());
        
        Payment payment = buildPayment(supplier, paymentRequestDTO);
        Payment savedPayment = paymentRepository.save(payment);
        
        List<PaymentsProducts> paymentProductsList = createPaymentProductsList(
            savedPayment, 
            paymentRequestDTO.getProducts()
        );
        List<PaymentsProducts> savedPaymentProducts = paymentsProductsRepository.saveAll(paymentProductsList);
        
        return buildPaymentResponseDTO(savedPayment, savedPaymentProducts);
    }

    @Transactional(readOnly = true)
    public List<PaymentCondition> getAllPaymentConditions() {
        return paymentConditionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SupplierPaymentsResponseDTO getAllPaymentsBySupplierId(int supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        List<Payment> supplierPayments = paymentRepository.findBySupplier(supplier);
        
        List<PaymentResponseDTO> paymentsResponses = supplierPayments.stream()
            .map(this::buildPaymentResponseDTO)
            .toList();
        
        SupplierPaymentsResponseDTO response = new SupplierPaymentsResponseDTO();
        response.setSupplierId(supplierId);
        response.setPayments(paymentsResponses);
        
        return response;
    }

    @Transactional(readOnly = true)
    public SupplierLastNextPaymentsResponseDTO getLastAndNextPaymentsBySupplierId(int supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        LocalDate today = LocalDate.now();

        Payment lastPayment = paymentRepository
            .findTopBySupplierAndPaymentDateLessThanEqualOrderByPaymentDateDesc(supplier, today).orElse(null);

        Payment nextPayment = paymentRepository
            .findTopBySupplierAndPaymentDateAfterOrderByPaymentDateAsc(supplier, today).orElse(null);

        PaymentResponseDTO lastPaymentDTO = lastPayment != null ? buildPaymentResponseDTO(lastPayment) : null;
        PaymentResponseDTO nextPaymentDTO = nextPayment != null ? buildPaymentResponseDTO(nextPayment) : null;

        SupplierLastNextPaymentsResponseDTO response = new SupplierLastNextPaymentsResponseDTO();
        response.setSupplierId(supplierId);
        response.setLast(lastPaymentDTO);
        response.setNext(nextPaymentDTO);

        return response;
    }

    @Transactional(readOnly = true)
    public List<UpcomingPaymentAlertDTO> getUpcomingPayments() {
        LocalDate today = LocalDate.now();
        LocalDate limitDate = today.plusDays(DAYS_TO_ALERT);

        List<Payment> upcomingPayments = paymentRepository.findByPaymentDateBetween(today, limitDate);

        return upcomingPayments.stream()
                .map(this::buildUpcomingPaymentAlertDTO)
                .toList();
    }

    private UpcomingPaymentAlertDTO buildUpcomingPaymentAlertDTO(Payment payment) {
        UpcomingPaymentAlertDTO dto = new UpcomingPaymentAlertDTO();
        dto.setPaymentId(payment.getId());
        dto.setSupplierId(payment.getSupplier().getId());
        dto.setSupplierName(payment.getSupplier().getName());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        dto.setDaysUntilPayment(ChronoUnit.DAYS.between(LocalDate.now(), payment.getPaymentDate()));
        dto.setPaymentCondition(payment.getSupplier().getPaymentCondition());

        List<ProductDTO> products = payment.getPaymentsProducts().stream()
                .map(pp -> productMapper.productToProductDTO(pp.getProduct()))
                .toList();
        dto.setProvidedProducts(products);

        return dto;
    }
    
    private Payment buildPayment(Supplier supplier, PaymentRequestDTO dto) {
        Payment payment = new Payment();
        payment.setSupplier(supplier);
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setNotes(dto.getNotes());
        payment.setAmount(calculateTotalAmount(dto.getProducts()));
        return payment;
    }

    private BigDecimal calculateTotalAmount(List<PaymentsProductsDTO> products) {
        return products.stream()
            .map(p -> p.getPricePerUnit().multiply(BigDecimal.valueOf(p.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<PaymentsProducts> createPaymentProductsList(Payment payment, List<PaymentsProductsDTO> productDTOs) {
        return productDTOs.stream()
            .map(dto -> createPaymentProduct(payment, dto))
            .toList();
    }

    private PaymentsProducts createPaymentProduct(Payment payment, PaymentsProductsDTO dto) {
        Product product = productService.findOrCreateProduct(dto.getProduct());
        
        PaymentsProducts paymentProduct = new PaymentsProducts();
        paymentProduct.setPayment(payment);
        paymentProduct.setProduct(product);
        paymentProduct.setQuantity(dto.getQuantity());
        paymentProduct.setPricePerUnit(dto.getPricePerUnit());
        paymentProduct.setTotalAmount(dto.getPricePerUnit().multiply(BigDecimal.valueOf(dto.getQuantity())));
        
        return paymentProduct;
    }

    private PaymentResponseDTO buildPaymentResponseDTO(Payment payment) {
        List<PaymentsProducts> paymentsProducts = payment.getPaymentsProducts();
        
        return buildPaymentResponseDTO(payment, paymentsProducts);
    }

    private PaymentResponseDTO buildPaymentResponseDTO(Payment payment, List<PaymentsProducts> paymentsProducts) {
        List<PaymentsProductsDTO> productDTOs = paymentsProductsMapper.paymentsProductsListToPaymentsProductsDTOList(paymentsProducts);
        
        PaymentResponseDTO response = paymentMapper.paymentToPaymentResponseDTO(payment);
        response.setProducts(productDTOs);
        
        return response;
    }

    private Supplier getSupplierOrThrow(int supplierId) {
        return supplierRepository.findById(supplierId)
            .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un proveedor con id: " + supplierId));
    }
}