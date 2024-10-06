package com.recipe.universe.domain.recipe.controller.form.dish;

import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import lombok.Data;

import java.util.List;

@Data
public class CreateDishForm {
    private String dishName;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer cookingTime;
    private Integer servingSize;
    private Integer recipeLevel;
    private Integer integeringredientsCnt;
    private String dishCategory;
    private List<GeneralStepForm> steps;
    private List<CreateDishIngredientForm> ingredients;
}
