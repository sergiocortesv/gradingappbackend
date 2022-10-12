package com.k1687.leisure.grading.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="critic")
@Getter
@Setter
@NoArgsConstructor
public class Critic {

    public Critic(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "critic_seq")
    private Long id;

    @Column(nullable=false, length=32)
    private String username;

    @OneToMany(mappedBy = "critic")
    @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
    private Set<Category> categories;

}
