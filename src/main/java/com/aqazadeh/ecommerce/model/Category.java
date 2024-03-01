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
 * Time: 16:14
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category extends BaseModel{

    @Column(unique = true)
    private String slug;

    private String name;

    @Lob
    private String about;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @ToString.Exclude
    private List<Category> children;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<Product> products;
}
