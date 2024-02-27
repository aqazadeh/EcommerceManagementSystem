package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.MediaDto;
import com.aqazadeh.ecommerce.model.Media;
import com.cloudinary.Cloudinary;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:18.02.2024
 * Time:12:12
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                Cloudinary.class
        }
)

public interface MediaMapper {
    @Named("mediaToDto")
    MediaDto toDto(Media media);

}
