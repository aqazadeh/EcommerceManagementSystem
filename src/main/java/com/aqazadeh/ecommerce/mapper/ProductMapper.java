package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.request.CreateProductRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateProductRequest;
import com.aqazadeh.ecommerce.dto.response.ProductDto;
import com.aqazadeh.ecommerce.model.Product;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:18:13
 */
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                ProductVariantMapper.class,
                MediaMapper.class
        }
)
public interface ProductMapper {
    @Mapping(target = "variants", qualifiedByName = "variantRequestToEntity")
    Product toEntity(CreateProductRequest request);

    @Mappings(value = {
            @Mapping(target = "variants", qualifiedByName = "variantToDto"),
            @Mapping(target = "media", qualifiedByName = "mediaToDto")
    })
    @Named("productToEntity")
    ProductDto toDto(Product product);

    Product toEntity(@MappingTarget Product product, UpdateProductRequest request);
}
