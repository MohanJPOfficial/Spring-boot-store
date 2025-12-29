package com.mohanjp.store.entity;

import com.mohanjp.store.cart.entity.CartEntity;
import com.mohanjp.store.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "store")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<OrderItemEntity> items = new LinkedHashSet<>();

    public static OrderEntity fromCart(CartEntity cart, UserEntity customer) {
        var order = new OrderEntity();
        order.setCustomer(customer);
        order.setStatus(PaymentStatus.PENDING);
        order.setTotalPrice(cart.getTotalPrice());

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItemEntity(order, item.getProduct(), item.getQuantity());
            order.items.add(orderItem);
        });

        return order;
    }

    public boolean isPlacedBy(UserEntity customer) {
        return this.customer.equals(customer);
    }
}

