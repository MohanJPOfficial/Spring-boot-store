package com.mohanjp.store.repositories;

import com.mohanjp.store.data.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
}