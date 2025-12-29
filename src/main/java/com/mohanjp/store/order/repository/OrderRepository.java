package com.mohanjp.store.order.repository;

import com.mohanjp.store.order.entity.OrderEntity;
import com.mohanjp.store.user.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM OrderEntity o WHERE o.customer = :customer")
    List<OrderEntity> getOrdersByCustomer(@Param("customer") UserEntity customer);

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM OrderEntity o WHERE o.id = :orderId")
    Optional<OrderEntity> getOrderWithItems(@Param("orderId") long orderId);
}
