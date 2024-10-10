package com.recipe.universe.domain.recipe.controller.form.recipe;

import com.recipe.universe.domain.recipe.controller.form.ingredient.UpdateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.UpdateStepForm;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRecipeForm {
    private String name;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer servingSize;
    private Integer difficulty;
    private String dishCategory;
    private List<UpdateStepForm> steps;
    private List<UpdateDishIngredientForm> dishIngredients;
}
