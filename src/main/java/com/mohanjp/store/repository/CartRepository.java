package com.mohanjp.store.repository;

import com.mohanjp.store.entity.CartEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT c FROM CartEntity c WHERE c.id = :cartId")
    Optional<CartEntity> getCartWithItems(@Param("cartId") UUID cartId);
}
