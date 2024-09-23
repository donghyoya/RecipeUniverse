package com.recipe.universe.domain.dish.controller.form;

import lombok.Data;
import lombok.Getter;

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
    List<GeneralRecipeForm> recipes;
}
