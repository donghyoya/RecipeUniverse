package com.recipe.universe.domain.dish.step.dto;

import com.recipe.universe.domain.dish.step.entity.RecipeStep;
import lombok.Getter;

@Getter
public class RecipeDto {
    private Long id;
    private Long recipeNum;
    private String description;

    public RecipeDto(RecipeStep recipeStep) {
        this.id = recipeStep.getId();
        this.recipeNum = recipeStep.getOrder();
        this.description = recipeStep.getDescription();
    }
}
