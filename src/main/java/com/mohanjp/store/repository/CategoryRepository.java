package com.mohanjp.store.repository;

import com.mohanjp.store.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Byte> {
}