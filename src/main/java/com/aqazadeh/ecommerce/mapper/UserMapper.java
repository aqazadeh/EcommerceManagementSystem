package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.UserDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.request.UserRegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:15:01
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toUserDto(User user);

    User toUser(UserRegisterRequest request);
    User toUser(@MappingTarget User user, UpdateUserRequest request);
}
