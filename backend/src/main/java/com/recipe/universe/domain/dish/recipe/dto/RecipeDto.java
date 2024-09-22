package com.recipe.universe.domain.dish.recipe.dto;

import com.recipe.universe.domain.dish.recipe.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeDto {
    private Long id;
    private Long recipeNum;
    private String description;

    public RecipeDto(Recipe recipe) {
        this.id = recipe.getId();
        this.recipeNum = recipe.getRecipeNum();
        this.description = recipe.getDescription();
    }
}
