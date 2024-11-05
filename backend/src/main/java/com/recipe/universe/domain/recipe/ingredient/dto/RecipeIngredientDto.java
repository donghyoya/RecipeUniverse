package com.recipe.universe.domain.recipe.ingredient.dto;

import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class RecipeIngredientDto {
    @Schema(description = "재료 아이디(해당 요리에 대해서만 사용가능한 id임)")
    private Long id;
    @Schema(description = "재료명")
    private String name;
    @Schema(description = "재료 개수")
    private Double amount;
    @Schema(description = "재료 단위")
    private String unit;
    @Schema(description = "재료 소개")
    private String description;
    @Schema(description = "재료는 필수인가?", example = "false")
    private Boolean optional;

    public RecipeIngredientDto(RecipeIngredient ingredient){
        this.id = ingredient.getId();
        this.name = ingredient.getIngredient().getIngName();
        this.amount = ingredient.getAmount();
        this.unit = ingredient.getUnit();
        this.description = ingredient.getDescription();
        this.optional = ingredient.getOptional();
    }
}
