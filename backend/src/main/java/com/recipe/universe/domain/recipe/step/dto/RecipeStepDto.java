package com.recipe.universe.domain.recipe.step.dto;

import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecipeStepDto {
    private Long id;
    private Long order;
    private String description;
    private Integer cookingTime;

    public RecipeStepDto(RecipeStep recipeStep) {
        this.id = recipeStep.getId();
        this.order = recipeStep.getOrder();
        this.description = recipeStep.getDescription();
        this.cookingTime = recipeStep.getCookingTime();
    }
}
