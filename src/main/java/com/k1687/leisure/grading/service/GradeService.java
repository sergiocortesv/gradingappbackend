package com.k1687.leisure.grading.service;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Grade;
import com.k1687.leisure.grading.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findGradesByCategory(Long categoryId) {
        return gradeRepository.findByCategoryGradeSystem(categoryId);
    }

    public Grade findGradeById(Long id) {
        return gradeRepository.findById(id).get();
    }

    /**
     * Retrieves all the graded items for the specified category
     * @return
     */
    public List<Grade> getGradedItems(Long categoryId){
        return gradeRepository.findGradeItemByCategory(categoryId);
    }
}
