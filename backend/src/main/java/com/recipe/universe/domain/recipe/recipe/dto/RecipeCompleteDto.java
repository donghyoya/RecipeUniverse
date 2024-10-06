package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeCompleteDto {
    private RecipeDto recipe;
    private Integer stepSize;
    private List<RecipeStepDto> steps;
    private Integer ingredientCounts;
    private List<DishIngredientDto> ingredients;

    public RecipeCompleteDto(RecipeDto recipe, List<RecipeStepDto> steps, List<DishIngredientDto> ingredients) {
        this.recipe = recipe;
        this.steps = steps;
        this.stepSize = steps.size();
        this.ingredients = ingredients;
        this.ingredientCounts = ingredients.size();
    }
}