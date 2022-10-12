package com.k1687.leisure.grading.controller;

import com.k1687.leisure.grading.configuration.ORMUtil;
import com.k1687.leisure.grading.mapper.CategoryMapper;
import com.k1687.leisure.grading.mapper.GradeMapper;
import com.k1687.leisure.grading.mapper.ItemMapper;
import com.k1687.leisure.grading.dto.Category;
import com.k1687.leisure.grading.dto.Grade;
import com.k1687.leisure.grading.dto.Item;
import com.k1687.leisure.grading.service.CategoryService;
import com.k1687.leisure.grading.service.GradeService;
import com.k1687.leisure.grading.service.ItemGradeService;
import org.hibernate.Hibernate;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private ItemGradeService itemService;

    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
    private ItemMapper itemmapper = Mappers.getMapper(ItemMapper.class);

    private GradeMapper gradeMapper = Mappers.getMapper(GradeMapper.class);

    @GetMapping
    public List<Category> getCategories(){
        return categoryMapper.domainToDto(ORMUtil.initializeAndUnproxyList(categoryService.findAllCategories()));
    }

    @GetMapping
    @RequestMapping("/{id}/grades")
    public List<Grade> getGradesByCategory(@PathVariable("id") Long id){
        return gradeMapper.domainToDto(gradeService.getGradedItems(id));
    }

    @GetMapping
    @RequestMapping("/{id}/items")
    public List<Item> getItemsByCategory(@PathVariable("id") Long id){
        return itemmapper.domainToDto(itemService.findItemByCategory(id));
    }

    @PostMapping
    public Category newCategory(@RequestBody Category category){
        return categoryMapper.domainToDto(categoryService.saveCategory(categoryMapper.dtoToDomain(category)));
    }

}
