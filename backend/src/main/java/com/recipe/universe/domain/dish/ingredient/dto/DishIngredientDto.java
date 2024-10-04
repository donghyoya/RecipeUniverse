package com.recipe.universe.domain.dish.ingredient.dto;

import com.recipe.universe.domain.dish.ingredient.entity.DishIngredient;
import lombok.Getter;

@Getter
public class DishIngredientDto {
    private String name;
    private Double amount;
    private String unit;
    private String description;
    private Boolean optional;

    public DishIngredientDto(DishIngredient ingredient){
        this.name = ingredient.getIngredient().getIngName();
        this.amount = ingredient.getDAmount();
        this.unit = ingredient.getUnit();
        this.description = ingredient.getDescription();
        this.optional = ingredient.getOptional();
    }
}
