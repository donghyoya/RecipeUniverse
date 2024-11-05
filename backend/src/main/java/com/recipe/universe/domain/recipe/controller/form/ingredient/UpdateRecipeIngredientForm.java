package com.recipe.universe.domain.recipe.controller.form.ingredient;

import com.recipe.universe.domain.recipe.controller.form.UpdateMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateRecipeIngredientForm {
    @Schema(name = "수정할 재료의 id(재료의 고유 id가 아님)")
    private Long id;
    private UpdateMethod method;
    private String ingredientName;
    private Double amount;
    private String unit;
    private Boolean optional;
    private String description;
}
