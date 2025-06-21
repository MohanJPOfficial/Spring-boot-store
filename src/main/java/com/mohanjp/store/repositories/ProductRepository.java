package com.mohanjp.store.repositories;

import com.mohanjp.store.data.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    // name
    List<ProductEntity> findByName(String name);

    List<ProductEntity> findByNameLike(String name);

    List<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findByNameStartingWith(String name);

    List<ProductEntity> findByNameEndingWith(String name);

    // price
    List<ProductEntity> findByPrice(BigDecimal price);

    List<ProductEntity> findByPriceGreaterThan(BigDecimal price);

    List<ProductEntity> findByPriceLessThan(BigDecimal price);

    List<ProductEntity> findByPriceBetween(BigDecimal price1, BigDecimal price2);

    // null
    List<ProductEntity> findByDescriptionIsNull();

    List<ProductEntity> findByDescriptionIsNotNull();

    // limit (top / first)
    List<ProductEntity> findTop3ByOrderByPriceAsc();

    List<ProductEntity> findFirst3ByOrderByPriceAsc();
}