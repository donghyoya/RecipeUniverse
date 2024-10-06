package com.recipe.universe.domain.dish.dish.dto;

import com.recipe.universe.domain.dish.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.dish.step.dto.RecipeStepDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DishCompleteDto {
    private DishDto dish;
    private Integer stepSize;
    private List<RecipeStepDto> steps;
    private Integer ingredientCounts;
    private List<DishIngredientDto> ingredients;

    public DishCompleteDto(DishDto dish, List<RecipeStepDto> steps, List<DishIngredientDto> ingredients) {
        this.dish = dish;
        this.steps = steps;
        this.stepSize = steps.size();
        this.ingredients = ingredients;
        this.ingredientCounts = ingredients.size();
    }
}
