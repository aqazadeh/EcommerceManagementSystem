package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.CartDto;
import com.aqazadeh.ecommerce.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:20.02.2024
 * Time:16:33
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                ProductMapper.class,
                ProductVariantMapper.class
        }
)
public interface CartMapper {
    @Mappings({
            @Mapping(target = "product", qualifiedByName = "productToEntity"),
            @Mapping(target = "variant", qualifiedByName = "variantToDto")
    })
    CartDto toDto(Cart cart);
}
