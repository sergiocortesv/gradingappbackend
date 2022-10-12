package com.k1687.leisure.grading.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GradingSystem {

    private Long id;
    private String name;
    private Set<Grade> grades;

}
