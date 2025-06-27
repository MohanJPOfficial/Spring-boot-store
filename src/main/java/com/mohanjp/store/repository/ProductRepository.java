package com.mohanjp.store.repository;

import com.mohanjp.store.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = "category")
    List<ProductEntity> findByCategoryId(Byte categoryId);

    @EntityGraph(attributePaths = "category")
    @Query("SELECT p FROM ProductEntity p")
    List<ProductEntity> findAllWithCategory();
}
