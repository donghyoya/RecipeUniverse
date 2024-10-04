package com.recipe.universe.domain.dish.dish.dto;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DishCompleteDto {
    private DishDto dish;
    private Integer recipeCounts;
    private List<RecipeDto> recipes;
    private Integer ingredientCounts;
    private List<DishIngredientDto> ingredients;

    public DishCompleteDto(DishDto dish, List<RecipeDto> recipes, List<DishIngredientDto> ingredients) {
        this.dish = dish;
        this.recipes = recipes;
        this.recipeCounts = recipes.size();
        this.ingredients = ingredients;
        this.ingredientCounts = ingredients.size();
    }
}
