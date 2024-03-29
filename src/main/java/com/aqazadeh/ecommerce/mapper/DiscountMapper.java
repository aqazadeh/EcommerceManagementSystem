package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateDiscountRequest;
import com.aqazadeh.ecommerce.dto.response.DiscountDto;
import com.aqazadeh.ecommerce.model.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 9.02.2024
 * Time: 16:01
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiscountMapper {
    Discount toEntity(CreateDiscountRequest request);

    Discount toEntity(@MappingTarget Discount discount, UpdateDiscountRequest request);

    DiscountDto toDto(Discount discount);
}
