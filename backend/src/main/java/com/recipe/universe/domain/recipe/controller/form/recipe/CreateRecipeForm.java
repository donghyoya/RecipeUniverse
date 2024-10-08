package com.recipe.universe.domain.recipe.controller.form.recipe;

import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import lombok.Data;

import java.util.List;

@Data
public class CreateRecipeForm {
    private String name;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer servingSize;
    private Integer recipeLevel;
    private String dishCategory;
    private List<GeneralStepForm> steps;
    private List<CreateDishIngredientForm> ingredients;
}
