package com.recipe.universe.domain.recipe.controller.form.ingredient;

import lombok.Data;

@Data
public class CreateRecipeIngredientForm {
    private String ingredientName;
    private Double amount;
    private String unit;
    private Boolean optional;
    private String description;

    public CreateRecipeIngredientForm(String ingredientName, Double amount, String unit, Boolean optional, String description) {
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.unit = unit;
        this.optional = optional;
        this.description = description;
    }
}
