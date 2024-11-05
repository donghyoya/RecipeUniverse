package com.recipe.universe.domain.recipe.controller.form.recipe;

import com.recipe.universe.domain.recipe.controller.form.hashtag.UpdateHashTagForm;
import com.recipe.universe.domain.recipe.controller.form.ingredient.UpdateRecipeIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.UpdateStepForm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRecipeForm {
    private String name;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer servingSize;
    private Integer difficulty;
    private String dishCategory;
    @Schema(description = "변경할 조리단계")
    private List<UpdateStepForm> steps;
    @Schema(description = "변경할 레시피의 재료")
    private List<UpdateRecipeIngredientForm> recipeIngredients;
    @Schema(description = "변경할 레시피의 해시태그들")
    private List<UpdateHashTagForm> hashtags;
}
