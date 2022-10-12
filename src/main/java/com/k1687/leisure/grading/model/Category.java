package com.k1687.leisure.grading.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    private Long id;

    @Column(nullable = false, length=96)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    @JsonBackReference(value="category-items")
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="critic_id")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    private Critic critic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="grading_system")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    private GradingSystem gradingSystem;

}
