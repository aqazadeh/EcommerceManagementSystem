package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.CommentDto;
import com.aqazadeh.ecommerce.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 22.02.2024
 * Time: 18:21
 */

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                UserMapper.class
        }
)
public interface CommentMapper {

    @Mapping(target = "user", qualifiedByName = "toUserDto")
    CommentDto toDto(Comment comment);
}
