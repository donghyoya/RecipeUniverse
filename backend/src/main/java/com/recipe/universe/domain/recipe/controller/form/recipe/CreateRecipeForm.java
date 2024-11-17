package com.recipe.universe.domain.recipe.controller.form.recipe;

import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateRecipeIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "레시피 생성시 사용되는 form객체")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateRecipeForm {
    private String name;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer servingSize;
    @Schema(description = "요리 난이도", examples = {
            "Easy", "Normal", "Hard"
    })
    private RecipeDifficulty difficulty;
    private String dishCategory;

    @NotEmpty
    private List<GeneralStepForm> steps;

    @NotEmpty
    private List<CreateRecipeIngredientForm> ingredients;

    private List<String> tagnames;

}
