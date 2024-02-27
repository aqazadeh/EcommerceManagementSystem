package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.dto.request.UserRegisterRequest;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.model.User;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:2.02.2024
 * Time:15:01
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                AddressMapper.class
        }
)
public interface UserMapper {


    @Mapping(source = "addresses", target = "addresses", qualifiedByName = "toAddressDto")
    @Named("toUserDto")
    UserDto toDto(User user);

    User toEntity(UserRegisterRequest request);

    User toEntity(@MappingTarget User user, UpdateUserRequest request);
}
