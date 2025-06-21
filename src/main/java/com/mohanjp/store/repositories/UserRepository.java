package com.mohanjp.store.repositories;

import com.mohanjp.store.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @EntityGraph(attributePaths = {"addresses", "tags"})
    Optional<UserEntity> findByEmail(String email);

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from UserEntity u")
    List<UserEntity> findUsersWithAddresses();
}