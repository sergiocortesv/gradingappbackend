package com.k1687.leisure.grading.viewModel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.k1687.leisure.grading.model.GradingSystem;
import com.k1687.leisure.grading.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Setter
@Getter
public class Grade {

    private Long id;
    private String name;
    private Integer arrangement;
    private GradingSystem gradingSystem;
    private Set<Item> items;
}
