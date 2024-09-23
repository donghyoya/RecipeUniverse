package com.recipe.universe.domain.dish.dish.dto;

import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import com.recipe.universe.global.dto.BaseListResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class DishWithRecipeDto {
    private DishDto dish;
    private Integer recipeCounts;
    private List<RecipeDto> recipes;

    public DishWithRecipeDto(DishDto dish, List<RecipeDto> recipes) {
        this.dish = dish;
        this.recipes = recipes;
        this.recipeCounts = recipes.size();
    }
}
