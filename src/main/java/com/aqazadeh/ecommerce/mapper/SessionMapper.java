package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.response.SessionDto;
import com.aqazadeh.ecommerce.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface SessionMapper {
    Session toEntity(Session session);

    SessionDto toDto(Session session);
}