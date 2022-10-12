package com.k1687.leisure.grading.service;

import com.k1687.leisure.grading.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryServiceTest {

    private static final Long DEFAULT_CATEGORY = 1L;
    private CategoryService categoryService;

    @BeforeEach
    public void setup(){
        categoryService = new CategoryService();
    }

}
