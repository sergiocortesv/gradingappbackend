package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query("SELECT g FROM Category c JOIN c.gradingSystem gs JOIN gs.grades g WHERE c.id=:category_id")
    public List<Grade> findByCategoryGradeSystem(@Param("category_id") Long category_id);

    @Query("SELECT g FROM Category c JOIN c.gradingSystem gs JOIN gs.grades g " +
            "LEFT JOIN FETCH g.items WHERE c.id=:category_id")
    public List<Grade> findGradeItemByCategory(@Param("category_id") Long category_id);

}
