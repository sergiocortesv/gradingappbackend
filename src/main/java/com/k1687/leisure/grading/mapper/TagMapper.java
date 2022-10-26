package com.k1687.leisure.grading.mapper;

import com.k1687.leisure.grading.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TagMapper {

    Tag toDomain(com.k1687.leisure.grading.dto.Tag tag);

    @Mapping(target = "category", ignore = true)
    com.k1687.leisure.grading.dto.Tag toDto(Tag tag);
}
