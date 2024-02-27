package com.aqazadeh.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 24.02.2024
 * Time: 20:47
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;
}
