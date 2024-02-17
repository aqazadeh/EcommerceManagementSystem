package com.aqazadeh.ecommerce.mapper;

import com.aqazadeh.ecommerce.dto.InventoryDto;
import com.aqazadeh.ecommerce.dto.MediaDto;
import com.aqazadeh.ecommerce.dto.ProductDto;
import com.aqazadeh.ecommerce.model.Inventory;
import com.aqazadeh.ecommerce.model.Media;
import com.aqazadeh.ecommerce.model.Product;
import com.aqazadeh.ecommerce.request.CreateInventoryRequest;
import com.aqazadeh.ecommerce.request.CreateProductRequest;
import com.aqazadeh.ecommerce.request.UpdateProductRequest;
import org.mapstruct.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date:15.02.2024
 * Time:18:13
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(target = "inventory", ignore = true)
    Product toEntity(CreateProductRequest request);

    Inventory toEntity(CreateInventoryRequest request);

    @Mappings(value = {
            @Mapping(target = "inventory", qualifiedByName = "inventory"),
            @Mapping(target = "media", qualifiedByName = "media")
    })
    ProductDto toDto(Product product);

    @Named("inventory")
    InventoryDto toDto(Inventory inventory);

    @Named("media")
    MediaDto toDto(Media media);

    Product toEntity(@MappingTarget Product product, UpdateProductRequest request);
}
