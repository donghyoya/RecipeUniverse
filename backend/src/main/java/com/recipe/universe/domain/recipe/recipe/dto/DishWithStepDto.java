package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DishWithStepDto {
    private DishDto dish;
    private Integer stepSize;
    private List<RecipeStepDto> steps;

    public DishWithStepDto(DishDto dish, List<RecipeStepDto> steps) {
        this.dish = dish;
        this.steps = steps;
        this.stepSize = steps.size();
    }
}
