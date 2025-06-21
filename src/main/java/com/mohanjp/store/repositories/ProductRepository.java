package com.mohanjp.store.repositories;

import com.mohanjp.store.dto.ProductSummaryDto;
import com.mohanjp.store.entity.CategoryEntity;
import com.mohanjp.store.entity.ProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    // Find products whose prices are in a given range and sort by name
    // SQL or JPQL
    @Query("SELECT p FROM ProductEntity p join p.category where p.price between :min and :max order by p.name")
    List<ProductEntity> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.price = :price WHERE p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal price, Byte categoryId);

    @Query("SELECT p.id, p.name from ProductEntity p where p.category = :category")
    List<ProductSummaryDto> findByCategory(@Param("category") CategoryEntity category);
}