package com.recipe.universe.domain.recipe.step.dto;

import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecipeStepDto {
    @Schema(description = "step id(수정시 사용). 순서가 아님에 유의")
    private Long id;
    @Schema(description = "조리 단계")
    private Long order;
    @Schema(description = "각 조리단계 묘사")
    private String description;
    @Schema(description = "해당 조리단계에서 얼마나 걸리는가?")
    private Integer cookingTime;

    public RecipeStepDto(RecipeStep recipeStep) {
        this.id = recipeStep.getId();
        this.order = recipeStep.getOrder();
        this.description = recipeStep.getDescription();
        this.cookingTime = recipeStep.getCookingTime();
    }
}
