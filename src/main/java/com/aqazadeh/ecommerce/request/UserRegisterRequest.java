package com.aqazadeh.ecommerce.request;

import com.aqazadeh.ecommerce.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:31.01.2024
 * Time:18:57
 */

public record UserRegisterRequest(
        @NotEmpty String username,
        @NotEmpty @Email String email,
        @NotEmpty String password,
        @NotEmpty String firstName,
        @NotEmpty String lastName
) {

    public static User convert(UserRegisterRequest request){
        return User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }
}
