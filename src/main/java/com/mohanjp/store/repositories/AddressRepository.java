package com.mohanjp.store.repositories;

import com.mohanjp.store.data.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}