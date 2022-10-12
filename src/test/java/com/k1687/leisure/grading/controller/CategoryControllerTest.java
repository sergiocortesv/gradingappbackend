package com.k1687.leisure.grading.controller;


import com.k1687.leisure.grading.model.Grade;
import com.k1687.leisure.grading.model.GradingSystem;
import com.k1687.leisure.grading.service.CategoryService;
import com.k1687.leisure.grading.service.GradeService;
import com.k1687.leisure.grading.service.ItemGradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CategoryController.class)
public class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private GradeService gradeService;

    @MockBean
    private ItemGradeService itemGradeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getGradesByCategory_valid_id() throws Exception {
        List<Grade> mockGrades = new ArrayList<>();
        GradingSystem gradingSystem = new GradingSystem();
        gradingSystem.setId(1L);
        gradingSystem.setName("US");

        Grade grade = new Grade();
        grade.setName("A");
        grade.setId(1L);
        grade.setArrangement(1);
        grade.setGradingSystem(gradingSystem);
        mockGrades.add(grade);
        when(gradeService.getGradedItems(anyLong())).thenReturn(mockGrades);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/1/grades")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"name\":\"A\",\"id\":1,\"arrangement\":1,\"gradingSystem\":{\"id\":1,\"name\":\"US\"}}]";

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void getGradesByCategory_invalid_id_format() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/x/grades")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

}
