package com.k1687.leisure.grading.dto;

import com.k1687.leisure.grading.configuration.ItemCreate;
import com.k1687.leisure.grading.model.GradingSystem;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class Grade {

    @NotNull(groups = ItemCreate.class, message = "{validation.field.notempty}")
    private Long id;
    private String name;
    private Integer arrangement;
    private GradingSystem gradingSystem;

}
