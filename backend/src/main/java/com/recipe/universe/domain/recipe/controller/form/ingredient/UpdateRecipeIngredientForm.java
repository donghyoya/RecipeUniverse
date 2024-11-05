package com.recipe.universe.domain.recipe.controller.form.ingredient;

import com.recipe.universe.domain.recipe.controller.form.UpdateMethod;
import lombok.Data;

@Data
public class UpdateRecipeIngredientForm {
    private Long id;
    private UpdateMethod method;
    private String ingredientName;
    private Double amount;
    private String unit;
    private Boolean optional;
    private String description;
}
