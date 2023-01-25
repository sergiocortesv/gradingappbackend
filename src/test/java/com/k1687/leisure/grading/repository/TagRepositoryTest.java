package com.k1687.leisure.grading.repository;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = {"/testdata-postgres.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = {"/dropdata-postgres.sql"})
@Testcontainers
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void findByCategory_multipleTags(){
        Category cat = new Category();
        cat.setId(1L);

        List<Tag> tags = tagRepository.findByCategory(cat);
        assertNotNull(tags);
        assertEquals(3, tags.size());
    }

    @Test
    public void findByNameAndCategory_existentNameCategory(){

        Category cat = new Category();
        cat.setId(1L);

        List<Tag> tags = tagRepository.findByNameIgnoreCaseAndCategory("adventure", cat);
        assertNotNull(tags);
        assertEquals(1, tags.size());
    }

}
