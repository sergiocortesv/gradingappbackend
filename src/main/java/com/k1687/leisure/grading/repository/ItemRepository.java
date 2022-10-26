package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("SELECT distinct(i) FROM Item i LEFT JOIN FETCH i.grade LEFT JOIN FETCH i.tags WHERE i.category.id=:category_id")
    public List<Item> findItemsByCategory(@Param("category_id") Long category_id);

    @Query("SELECT i FROM Item i WHERE i.category.id=:category_id and lower(i.name) = lower(:item_name)")
    public List<Item> findItemsByNameAndCategory(@Param("category_id") Long category_id,
                                                 @Param("item_name") String item_name);
}
