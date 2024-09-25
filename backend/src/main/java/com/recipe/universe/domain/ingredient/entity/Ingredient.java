package com.recipe.universe.domain.ingredient.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue
    private int ingId;

    @Column(name = "ingredientName")
    private String ingName;

    @Column(name = "category")
    private String category;
}
