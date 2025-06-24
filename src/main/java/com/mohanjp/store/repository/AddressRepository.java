package com.mohanjp.store.repository;

import com.mohanjp.store.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}