package com.mohanjp.store.repositories;

import com.mohanjp.store.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {

    List<ProductEntity> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice);
}
