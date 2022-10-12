package com.k1687.leisure.grading.mapper;

import com.k1687.leisure.grading.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "critic", ignore = true)
    com.k1687.leisure.grading.dto.Category domainToDto(Category category);

    Category dtoToDomain(com.k1687.leisure.grading.dto.Category category);

    List<com.k1687.leisure.grading.dto.Category> domainToDto(List<Category> category);

}
