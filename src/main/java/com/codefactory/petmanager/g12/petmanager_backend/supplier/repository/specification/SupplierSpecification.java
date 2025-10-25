package com.codefactory.petmanager.g12.petmanager_backend.supplier.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import com.codefactory.petmanager.g12.petmanager_backend.supplier.model.Supplier;

public class SupplierSpecification {

  public static Specification<Supplier> nameContains(String name) {
    return (root, query, cb) ->
        name == null || name.isBlank() ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
  }

  public static Specification<Supplier> nitContains(String nit) {
    return (root, query, cb) ->
        nit == null || nit.isBlank() ? null : cb.like(root.get("nit"), "%" + nit + "%");
  }

  public static Specification<Supplier> addressContains(String address) {
    return (root, query, cb) ->
        address == null || address.isBlank() ? null : cb.like(cb.lower(root.get("address")), "%" + address.toLowerCase() + "%");
  }

  public static Specification<Supplier> paymentConditionEquals(Integer paymentConditionId) {
    return (root, query, cb) ->
        paymentConditionId == null ? null : cb.equal(root.get("paymentCondition").get("id"), paymentConditionId);
  }
}
