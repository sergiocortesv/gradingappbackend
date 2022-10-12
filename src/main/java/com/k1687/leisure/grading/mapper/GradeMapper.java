package com.k1687.leisure.grading.mapper;

import com.k1687.leisure.grading.model.Grade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface GradeMapper {

    public com.k1687.leisure.grading.dto.Grade domainToDto(Grade grade);

    public List<com.k1687.leisure.grading.dto.Grade> domainToDto(List<Grade> grade);

}
