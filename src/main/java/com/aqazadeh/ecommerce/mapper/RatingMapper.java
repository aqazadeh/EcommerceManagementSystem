package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateRatingRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateRatingRequest;
import com.aqazadeh.ecommerce.dto.response.RatingDto;
import com.aqazadeh.ecommerce.model.Rating;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 23.02.2024
 * Time: 00:56
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                UserMapper.class,
                MediaMapper.class
        }
)
public interface RatingMapper {
    @Mappings(value = {
            @Mapping(target = "media", qualifiedByName = "mediaToDto"),
            @Mapping(target = "user", qualifiedByName = "toUserDto")
    })
    RatingDto toDto(Rating rating);

    Rating toEntity(CreateRatingRequest request);

    Rating toEntity(@MappingTarget Rating rating, UpdateRatingRequest request);
}
