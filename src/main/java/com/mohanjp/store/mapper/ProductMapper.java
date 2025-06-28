package com.mohanjp.store.mapper;

import com.mohanjp.store.dto.ProductDto;
import com.mohanjp.store.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(ProductEntity product);

    ProductEntity toEntity(ProductDto request);

    @Mapping(target = "id", ignore = true)
    void update(ProductDto request, @MappingTarget ProductEntity product);
}
