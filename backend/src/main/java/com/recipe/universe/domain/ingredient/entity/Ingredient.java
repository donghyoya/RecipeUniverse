package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue
    private Long ingId;

    @Column(name = "ingredientName")
    private String ingName;

    @Column(name = "category")
    private String category;

    @Builder
    public Ingredient(String ingName, String category){
        this.ingName = ingName;
        this.category = category;
    }
}
