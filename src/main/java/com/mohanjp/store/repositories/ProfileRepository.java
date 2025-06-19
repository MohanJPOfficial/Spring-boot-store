package com.mohanjp.store.repositories;

import com.mohanjp.store.data.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {
}