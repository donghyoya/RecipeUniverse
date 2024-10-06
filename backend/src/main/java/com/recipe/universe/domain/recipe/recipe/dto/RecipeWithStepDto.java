package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import lombok.Getter;

import java.util.List;

@Getter
public class RecipeWithStepDto {
    private RecipeDto recipe;
    private Integer stepSize;
    private List<RecipeStepDto> steps;

    public RecipeWithStepDto(RecipeDto recipe, List<RecipeStepDto> steps) {
        this.recipe = recipe;
        this.steps = steps;
        this.stepSize = steps.size();
    }
}
