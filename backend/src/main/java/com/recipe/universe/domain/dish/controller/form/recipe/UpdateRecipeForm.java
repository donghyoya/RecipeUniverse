package com.recipe.universe.domain.dish.controller.form.recipe;

import com.recipe.universe.domain.dish.controller.form.UpdateMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "recipe를 bulk update하기 위한 form객체")
public class UpdateRecipeForm {
    @Schema(description = "Dish에 대한 ID", example = "1")
    private Long id;

    private UpdateMethod method;

    @Schema(description = "해당 레시피가 생성 및 갱신되는 경우 레시피에 대한 내부 내용")
    private GeneralRecipeForm data;
}
