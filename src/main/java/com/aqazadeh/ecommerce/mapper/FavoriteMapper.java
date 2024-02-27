package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.FavoriteDto;
import com.aqazadeh.ecommerce.model.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 25.02.2024
 * Time: 00:40
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface FavoriteMapper {

    FavoriteDto toDto(Favorite favorite);
}
