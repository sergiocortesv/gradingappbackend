package com.k1687.leisure.grading.mapper;

import com.k1687.leisure.grading.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses=TagMapper.class)
public interface ItemMapper {
    Item sourceToDest(com.k1687.leisure.grading.dto.Item item);
    @Mapping(target = "category", ignore = true)
    com.k1687.leisure.grading.dto.Item destToSource(Item item);

    List<com.k1687.leisure.grading.dto.Item> domainToDto(List<Item> items);
}
