package com.k1687.leisure.grading.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="item")
@Setter
@Getter
public class Item {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
   private Long id;

   @Column(nullable=false, length=96)
   private String name;

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
   private Category category;

   @ManyToOne(fetch = FetchType.LAZY)
   @JsonInclude(value= JsonInclude.Include.NON_NULL, content= JsonInclude.Include.NON_NULL)
   private Grade grade;

}
