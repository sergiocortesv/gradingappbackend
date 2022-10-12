package com.k1687.leisure.grading.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "grading_system")
@Setter
@Getter
public class GradingSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grading_system_seq")
    private Long id;

    @Column(nullable = false, length=96)
    private String name;

    @OneToMany(mappedBy = "gradingSystem")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    private Set<Grade> grades;

}
