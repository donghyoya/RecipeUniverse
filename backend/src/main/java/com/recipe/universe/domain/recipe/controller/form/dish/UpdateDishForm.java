package com.recipe.universe.domain.recipe.controller.form.dish;

import com.recipe.universe.domain.recipe.controller.form.ingredient.UpdateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.UpdateStepForm;
import lombok.Data;

import java.util.List;

@Data
public class UpdateDishForm {
    private String dishName;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer servingSize;
    private Integer recipeLevel;
    private Integer ingredientsCnt;
    private String dishCategory;
    private List<UpdateStepForm> steps;
    private List<UpdateDishIngredientForm> dishIngredients;
}