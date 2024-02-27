package com.aqazadeh.ecommerce.model;

import com.aqazadeh.ecommerce.model.enums.MediaRelationType;
import com.aqazadeh.ecommerce.model.enums.MediaType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 6.02.2024
 * Time: 14:59
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Enumerated(EnumType.STRING)
    private MediaRelationType relationType;

    private String url;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    @ToString.Exclude
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

}

