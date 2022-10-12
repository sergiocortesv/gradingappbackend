package com.k1687.leisure.grading.service;

import com.k1687.leisure.grading.model.Category;
import com.k1687.leisure.grading.model.Critic;
import com.k1687.leisure.grading.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private static final Critic DEFAULT_CRITIC = new Critic(1L);
    public List<Category> findAllCategories() {
        List<Category> cats = categoryRepository.findCategoryByCritic(DEFAULT_CRITIC.getId());
        cats.stream().forEach(x -> System.out.println(x.getGradingSystem().getGrades().size()));
        return cats;
    }

    public Category saveCategory(Category category) {
        category.setCritic(DEFAULT_CRITIC);
        return categoryRepository.save(category);
    }

}
