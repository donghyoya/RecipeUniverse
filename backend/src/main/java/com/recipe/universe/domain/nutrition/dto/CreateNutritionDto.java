package com.recipe.universe.domain.nutrition.dto;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNutritionDto {

    private Double calories;
    private Double carbs;
    private Double protein;
    private Double fat;
    private Double sugar;
    private Double sodium;
    private Double moisture;
    private Double potassium;
    private Double calcium;

    private Double nAmount;
    private Recipe recipe;
    private Ingredient ingredient;

    public CreateNutritionDto(Double calories, Double carbs, Double protein, Double fat,
                              Double sugar, Double sodium, Double moisture,
                              Double potassium, Double calcium,
                              Double nAmount) {
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.sodium = sodium;
        this.moisture = moisture;
        this.potassium = potassium;
        this.calcium = calcium;

        this.nAmount = nAmount;
    }

}