package com.example.groceryorderapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@Data
@Table(name = "meals")
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String type;
}
