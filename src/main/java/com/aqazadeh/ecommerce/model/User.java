package com.aqazadeh.ecommerce.model;

import com.aqazadeh.ecommerce.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 31.01.2024
 * Time: 15:51
 */
@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private String confirmationToken;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<UserAddress> addresses;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Cart> cart;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Session> sessions;


    //Security
    private boolean accountNonExpired;
    private boolean isEnabled;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<Role> authorities = new HashSet<>(Set.of(Role.USER));
}
