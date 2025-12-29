package com.mohanjp.store.product.repository;

import com.mohanjp.store.product.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Byte> {
}