package com.mohanjp.store.services;

import com.mohanjp.store.data.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Byte> {
}