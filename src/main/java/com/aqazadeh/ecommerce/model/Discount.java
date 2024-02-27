package com.aqazadeh.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 16:42
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String about;

    private Integer discountPercent;

    private LocalDateTime expiredTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;
}
