package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Grade;
import com.k1687.leisure.grading.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void findItemsByCategoryId_multipleItems(){
        List<Item> items = itemRepository.findItemsByCategory(1L);
        assertEquals(5, items.size());
    }

    @Test
    public void saveGradedItem_valid_item(){
        Item item = new Item();
        item.setName("Item Name");

        Grade grade = new Grade();
        grade.setId(1L);

        Category category = new Category();
        category.setId(1L);

        item.setGrade(grade);
        item.setCategory(category);

        Item savedItem = itemRepository.save(item);
        assertEquals("Item Name", savedItem.getName());
    }

    @Test
    public void deleteGradedItem_valid_item(){
        Item item = new Item();
        item.setId(1L);
        itemRepository.delete(item);
    }
}
