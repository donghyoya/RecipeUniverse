package com.recipe.universe.domain.dish.controller.form.ingredient;

import lombok.Data;

@Data
public class DishIngredientForm {
    private String ingredientName;
    private Double amount;
    private String unit;
}
