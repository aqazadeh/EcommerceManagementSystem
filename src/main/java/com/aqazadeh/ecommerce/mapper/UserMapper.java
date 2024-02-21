package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.UserAddress;
import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.dto.request.UserRegisterRequest;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:15:01
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserAddress toAddress(CreateUserAddressRequest request);

    UserAddress toAddress(@MappingTarget UserAddress address, UpdateUserAddressRequest request);
    @Named("toAddressDto")
    UserAddressDto toAddressDto(UserAddress address);

    @Mapping(source = "addresses", target = "addresses", qualifiedByName = "toAddressDto")
    UserDto toUserDto(User user);

    User toUser(UserRegisterRequest request);

    User toUser(@MappingTarget User user, UpdateUserRequest request);
}
