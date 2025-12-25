package com.mohanjp.store.repository;

import com.mohanjp.store.entity.OrderEntity;
import com.mohanjp.store.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM OrderEntity o WHERE o.customer = :customer")
    List<OrderEntity> getAllByCustomer(@Param("customer") UserEntity customer);
}
