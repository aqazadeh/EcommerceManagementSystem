package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.DiscountDto;
import com.aqazadeh.ecommerce.model.Discount;
import com.aqazadeh.ecommerce.request.CreateDiscountRequest;
import com.aqazadeh.ecommerce.request.UpdateDiscountRequest;
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
    Discount toDiscount(CreateDiscountRequest request);

    Discount toDiscount(@MappingTarget Discount discount, UpdateDiscountRequest request);

    DiscountDto toDiscountDto(Discount discount);
}
