package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.grade WHERE i.category.id=:category_id")
    public List<Item> findItemsByCategory(@Param("category_id") Long category_id);

}
