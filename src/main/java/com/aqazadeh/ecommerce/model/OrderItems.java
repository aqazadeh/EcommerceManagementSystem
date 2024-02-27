package com.aqazadeh.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 16:56
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    @ToString.Exclude
    private ProductVariant variants;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    private Order order;
}
