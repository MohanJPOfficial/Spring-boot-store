package com.mohanjp.store.user.repository;

import com.mohanjp.store.user.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {

}