package com.k1687.leisure.grading.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Critic {

    private Long id;
    private String username;
    private Set<Category> categories;

}
