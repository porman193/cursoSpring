package com.and.pizzas.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @Column(name = "quantity", nullable = false, precision = 2, scale = 1)
    private BigDecimal quantity;

    @Column(name = "price", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @MapsId("idOrder")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_order", nullable = false,referencedColumnName = "id_order",insertable = false,updatable = false)
    private Oder order;

    @OneToOne
    @JoinColumn(name = "id_pizza", nullable = false,referencedColumnName = "id_pizza",insertable = false,updatable = false)
    private Pizza pizza;

}