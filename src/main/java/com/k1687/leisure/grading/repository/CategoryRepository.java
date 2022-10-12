package com.k1687.leisure.grading.repository;


import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Critic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT distinct(g) " +
           "FROM Category g LEFT JOIN FETCH g.critic cri LEFT JOIN FETCH g.gradingSystem gs LEFT JOIN FETCH gs.grades gra " +
           "WHERE cri.id = :critic_id")
    List<Category> findCategoryByCritic(@Param("critic_id") Long critic_id);

    List<Category> findByCritic(Critic critic);

}
