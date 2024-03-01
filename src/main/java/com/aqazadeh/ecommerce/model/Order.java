package com.aqazadeh.ecommerce.model;

import com.aqazadeh.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 16:45
 */
@Entity
@Table(name = "product_orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Order extends BaseModel {

    @Builder.Default
    private OrderStatus status = OrderStatus.WAITING;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    @ToString.Exclude
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItems> orderItems;
}