package com.k1687.leisure.grading.controller;

import com.k1687.leisure.grading.dto.Grade;
import com.k1687.leisure.grading.mapper.GradeMapper;
import com.k1687.leisure.grading.service.GradeService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;
    
    private GradeMapper gradeMapper = Mappers.getMapper(GradeMapper.class);
    
    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable("id") Long id){
        return gradeMapper.domainToDto(gradeService.findGradeById(id));
    }

}
