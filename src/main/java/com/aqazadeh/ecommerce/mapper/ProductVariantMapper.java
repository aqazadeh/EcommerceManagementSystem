package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateVariantRequest;
import com.aqazadeh.ecommerce.dto.response.ProductVariantDto;
import com.aqazadeh.ecommerce.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:18.02.2024
 * Time:12:07
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductVariantMapper {

    @Named("variantToDto")
    ProductVariantDto toDto(ProductVariant productVariant);

    //    ProductVariant toDto(@MappingTarget ProductVariant productVariant, UpdateIn);
    @Named("variantRequestToEntity")
    ProductVariant toEntity(CreateVariantRequest request);
}
