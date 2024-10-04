package com.recipe.universe.domain.dish.controller.form.ingredient;

import lombok.Data;

@Data
public class CreateDishIngredientForm {
    private String ingredientName;
    private Double amount;
    private String unit;
    private Boolean optional;
    private String description;
}
