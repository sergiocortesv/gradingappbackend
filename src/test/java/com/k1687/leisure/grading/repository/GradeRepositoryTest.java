package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Grade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    @Test
    public void findByCategory(){
        List<Grade> grades= gradeRepository.findByCategoryGradeSystem(1L);
        assertNotNull(grades);
    }

    @Test
    public void findGradeItemsByCategory(){
        List<Grade> grades = gradeRepository.findGradeItemByCategory(1L);
        // TODO Review
        assertEquals(7, grades.size());
    }
}
