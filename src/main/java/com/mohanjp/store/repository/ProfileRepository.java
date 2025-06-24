package com.mohanjp.store.repository;

import com.mohanjp.store.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {

}