package com.aqazadeh.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 16:05
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product extends BaseModel{
    private String name;

    private String slug;
    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<ProductVariant> variants;

    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private Discount discount;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Media> media;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Comment> comments;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Rating> ratings;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
}
