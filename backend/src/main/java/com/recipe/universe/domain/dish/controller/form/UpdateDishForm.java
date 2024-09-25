package com.recipe.universe.domain.dish.controller.form;

import jakarta.validation.constraints.NotNull;
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
    private List<UpdateRecipeForm> recipes;
}
