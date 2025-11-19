package com.codefactory.petmanager.g12.petmanager_backend.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codefactory.petmanager.g12.petmanager_backend.payment.controller.dto.ProductDTO;
import com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto.SupplierProductsResponse;
import com.codefactory.petmanager.g12.petmanager_backend.product.controller.dto.SuppliersProductsDTO;
import com.codefactory.petmanager.g12.petmanager_backend.product.mapper.ProductMapper;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.Product;
import com.codefactory.petmanager.g12.petmanager_backend.product.model.SuppliersProducts;
import com.codefactory.petmanager.g12.petmanager_backend.product.repository.ProductRepository;
import com.codefactory.petmanager.g12.petmanager_backend.product.repository.SuppliersProductsRepository;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final SuppliersProductsRepository suppliersProductsRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public SupplierProductsResponse findProductsBySupplierId(int supplierId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        List<SuppliersProducts> suppliersProducts = suppliersProductsRepository.findBySupplier(supplier);
        if (suppliersProducts.isEmpty()) {
            return new SupplierProductsResponse(supplier.getId(), supplier.getName(), List.of());
        }
        return new SupplierProductsResponse(supplier.getId(), supplier.getName(), buildSuppliersProductsDTOs(suppliersProducts));
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findOrCreateProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        
        if (productRepository.existsByNameIgnoreCaseAndBrandIgnoreCase(
                product.getName(), 
                product.getBrand())) {
            return productRepository.findByNameIgnoreCaseAndBrandIgnoreCase(
                product.getName(), 
                product.getBrand()
            );
        }
        
        return productRepository.save(product);
    }

    @Transactional
    public SuppliersProducts addSupplierProduct(int supplierId, int productId) {
        Supplier supplier = getSupplierOrThrow(supplierId);
        Product product = getProductOrThrow(productId);
        
        SuppliersProducts supplierProduct = new SuppliersProducts();
        supplierProduct.setSupplier(supplier);
        supplierProduct.setProduct(product);
        return suppliersProductsRepository.save(supplierProduct);
    }

    @Transactional
    public void deleteSupplierProduct(int suppliersProductsId) {
        if (!suppliersProductsRepository.existsById(suppliersProductsId)) {
            throw new EntityNotFoundException("No se ha encontrado una relación proveedor-producto con id: " + suppliersProductsId);
        }
        suppliersProductsRepository.deleteById(suppliersProductsId);
    }

    private List<SuppliersProductsDTO> buildSuppliersProductsDTOs(List<SuppliersProducts> suppliersProducts) {
        return suppliersProducts.stream()
            .map(sp -> new SuppliersProductsDTO(sp.getId(), sp.getProduct()))
            .toList();
    }

    private Product getProductOrThrow(int productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un producto con id: " + productId));
    }

    private Supplier getSupplierOrThrow(int supplierId) {
        return supplierRepository.findById(supplierId)
            .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado un proveedor con id: " + supplierId));
    }
    
}