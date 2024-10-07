package com.recipe.universe.domain.recipe.ingredient.dto;

import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import lombok.Getter;

@Getter
public class DishIngredientDto {
    private Long id;
    private String name;
    private Double amount;
    private String unit;
    private String description;
    private Boolean optional;

    public DishIngredientDto(RecipeIngredient ingredient){
        this.id = ingredient.getId();
        this.name = ingredient.getIngredient().getIngName();
        this.amount = ingredient.getAmount();
        this.unit = ingredient.getUnit();
        this.description = ingredient.getDescription();
        this.optional = ingredient.getOptional();
    }
}
