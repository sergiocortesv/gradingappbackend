package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Critic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByCategory_singleResult(){
        Critic theCritic = new Critic();
        theCritic.setId(1L);
        List<Category> categoryList = categoryRepository.findByCritic(theCritic);
        assertNotNull(categoryList);
        assertEquals(2, categoryList.size());
        assertEquals("Category 1", categoryList.get(0).getName());
    }

    @Test
    public void findCategoryByCritic_singleResult(){
        List<Category> cats = categoryRepository.findCategoryByCritic(1L);
        assertNotNull(cats);
        assertEquals(2, cats.size());
        cats.stream().forEach( x -> System.out.println(x.getGradingSystem().getName()));
    }

}
