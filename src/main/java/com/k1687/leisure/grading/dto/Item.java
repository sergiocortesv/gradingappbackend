package com.k1687.leisure.grading.dto;

import com.k1687.leisure.grading.configuration.APIAll;
import com.k1687.leisure.grading.configuration.APICreate;
import com.k1687.leisure.grading.configuration.ItemCreate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Getter
@Setter
public class Item {

    @Null(groups = ItemCreate.class, message = "{item.id.notrequired}")
    private Long id;
    @NotEmpty(message = "{validation.field.notempty}", groups = APIAll.class)
    private String name;
    @Valid
    private Category category;
    @Valid
    private Grade grade;

    private List<Tag> tags;

}
