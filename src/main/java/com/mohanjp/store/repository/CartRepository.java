package com.mohanjp.store.repository;

import com.mohanjp.store.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {
}
