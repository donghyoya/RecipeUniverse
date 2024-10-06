package com.recipe.universe.domain.recipe.step.dto;

import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import lombok.Getter;

@Getter
public class RecipeStepDto {
    private Long id;
    private Long order;
    private String description;

    public RecipeStepDto(RecipeStep recipeStep) {
        this.id = recipeStep.getId();
        this.order = recipeStep.getOrder();
        this.description = recipeStep.getDescription();
    }
}