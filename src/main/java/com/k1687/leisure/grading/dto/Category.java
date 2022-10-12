package com.k1687.leisure.grading.dto;

import com.k1687.leisure.grading.configuration.APIAll;
import com.k1687.leisure.grading.model.GradingSystem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Category {

    @NotNull(groups = APIAll.class)
    private Long id;
    private String name;
    private Set<Item> items = new HashSet<>();
    private Critic critic;
    private GradingSystem gradingSystem;

}
