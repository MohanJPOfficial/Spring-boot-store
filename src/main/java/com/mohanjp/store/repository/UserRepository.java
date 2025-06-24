package com.mohanjp.store.repository;

import com.mohanjp.store.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}