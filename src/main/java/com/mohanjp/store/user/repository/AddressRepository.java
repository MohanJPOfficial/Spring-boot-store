package com.mohanjp.store.user.repository;

import com.mohanjp.store.user.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}