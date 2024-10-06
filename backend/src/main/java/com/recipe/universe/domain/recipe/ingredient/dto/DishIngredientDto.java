package com.recipe.universe.domain.recipe.ingredient.dto;

import com.recipe.universe.domain.recipe.ingredient.entity.DishIngredient;
import lombok.Getter;

@Getter
public class DishIngredientDto {
    private Long id;
    private String name;
    private Double amount;
    private String unit;
    private String description;
    private Boolean optional;

    public DishIngredientDto(DishIngredient ingredient){
        this.id = ingredient.getDiId();
        this.name = ingredient.getIngredient().getIngName();
        this.amount = ingredient.getDAmount();
        this.unit = ingredient.getUnit();
        this.description = ingredient.getDescription();
        this.optional = ingredient.getOptional();
    }
}
