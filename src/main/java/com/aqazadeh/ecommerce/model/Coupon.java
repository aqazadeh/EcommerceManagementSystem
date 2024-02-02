package com.aqazadeh.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 16:52
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coupon {
    @Id
    @GeneratedValue
    private Long id;

    private Double percentCount;

    private String code;

    private String name;

    @Lob
    private String about;

    private LocalDateTime expiredTime;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
