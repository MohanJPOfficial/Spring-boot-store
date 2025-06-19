package com.mohanjp.store.repositories;

import com.mohanjp.store.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}