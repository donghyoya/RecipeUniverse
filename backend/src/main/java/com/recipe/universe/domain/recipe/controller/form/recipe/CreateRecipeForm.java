package com.recipe.universe.domain.recipe.controller.form.recipe;

import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CreateRecipeForm {
    private String name;
    private String description;
    private String cuisineType;
    private String mealType;
    private Integer preparationTime;
    private Integer servingSize;
    private Integer difficulty;
    private String dishCategory;

    @NotEmpty
    private List<GeneralStepForm> steps;

    @NotEmpty
    private List<CreateDishIngredientForm> ingredients;

    private List<String> tagnames;
}
