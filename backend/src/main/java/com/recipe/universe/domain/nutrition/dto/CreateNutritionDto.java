package com.recipe.universe.domain.nutrition.dto;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.nutrition.entity.Nutrition;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    private Double nAmount;
    private Dish dish;
    private Ingredient ingredient;

    public CreateNutritionDto(Double calories, Double carbs, Double protein, Double fat, Double sugar, Double sodium, Double nAmount) {
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.sodium = sodium;
        this.nAmount = nAmount;
    }

    public CreateNutritionDto(Nutrition nutrition){
        this.calories = nutrition.getCalories();
        this.carbs = nutrition.getCarbs();
        this.protein = nutrition.getProtein();
        this.fat = nutrition.getFat();
        this.sugar = nutrition.getSugar();
        this.sodium = nutrition.getSodium();
        this.nAmount = nutrition.getNAmount();
    }

}
