package com.selimsahin.readingisgood.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Transactional
@Entity
@Table(name = "orderedBook")
public class OrderedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String bookName;
    @Min(1)
    private Integer amount;
    @JsonIgnore
    @ManyToOne
    private Orders orders;
}
