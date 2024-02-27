package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.model.UserAddress;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 27.02.2024
 * Time: 00:02
 */
@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface AddressMapper {

    UserAddress toAddress(CreateUserAddressRequest request);

    UserAddress toAddress(@MappingTarget UserAddress address, UpdateUserAddressRequest request);

    @Named("toAddressDto")
    UserAddressDto toAddressDto(UserAddress address);
}
