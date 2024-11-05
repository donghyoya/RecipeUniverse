package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.recipe.ingredient.dto.RecipeIngredientDto;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RecipeCompleteDto {
    @Schema(description = "레시피 정보")
    private RecipeDto recipe;
    @Schema(description = "조리 단계가 몇개가 있는가?")
    private Integer stepSize;
    @Schema(description = "각 조리단계에 대한 소개")
    private List<RecipeStepDto> steps;
    @Schema(description = "재료가 몇가 있는가?")
    private Integer ingredientCounts;
    @Schema(description = "개별 재료에 대한 데이터")
    private List<RecipeIngredientDto> ingredients;

    public RecipeCompleteDto(RecipeDto recipe, List<RecipeStepDto> steps, List<RecipeIngredientDto> ingredients) {
        this.recipe = recipe;
        this.steps = steps;
        this.stepSize = steps.size();
        this.ingredients = ingredients;
        this.ingredientCounts = ingredients.size();
    }
}
