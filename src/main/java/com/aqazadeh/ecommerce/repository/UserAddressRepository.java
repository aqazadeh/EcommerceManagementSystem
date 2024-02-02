package com.aqazadeh.ecommerce.repository;

import com.aqazadeh.ecommerce.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:48
 */

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
