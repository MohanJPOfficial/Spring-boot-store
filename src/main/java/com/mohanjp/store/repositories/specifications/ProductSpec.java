package com.mohanjp.store.repositories.specifications;

import com.mohanjp.store.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {

    public static Specification<ProductEntity> hasName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }

    public static Specification<ProductEntity> hasPriceGreaterThanOrEqualTo(BigDecimal minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<ProductEntity> hasPriceLessThanOrEqualTo(BigDecimal maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<ProductEntity> productsByCategory(Byte categoryId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }
}
