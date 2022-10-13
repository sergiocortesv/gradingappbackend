package com.k1687.leisure.grading.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="grade")
@Setter
@Getter
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    private Long id;

    @Column(nullable=false, length=3)
    private String name;

    @Column(nullable = false)
    private Integer arrangement;

    @Column(nullable = true)
    private String cssColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grading_system_id", updatable = false, insertable = false)
    @JsonInclude(value= JsonInclude.Include.NON_EMPTY, content= JsonInclude.Include.NON_NULL)
    @JsonBackReference(value="grade-gradingSystem")
    private GradingSystem gradingSystem;

    @OneToMany(mappedBy = "grade")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    @JsonBackReference(value="grade-items")
    private Set<Item> items;

}
