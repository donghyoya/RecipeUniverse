package com.recipe.universe.domain.nutrition.entity;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLRestriction("del_flag = false")
@NoArgsConstructor
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    private Double calories;
    private Double carbs;
    private Double protein;
    private Double fat;
    private Double sugar;
    private Double sodium;

    @Column(name = "nutritionAmount")
    private Double nAmount;

    @OneToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

    @OneToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingId")
    private Ingredient ingredient;

    @Builder
    public Nutrition(Double calories, Double carbs, Double protein, Double fat, Double sugar, Double sodium, Double nAmount, Dish dish, Ingredient ingredient) {
        this.calories = calories;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.sugar = sugar;
        this.sodium = sodium;
        this.nAmount = nAmount;
        this.dish = dish;
        this.ingredient = ingredient;
    }

    public Nutrition(CreateNutritionDto dto){
        this.calories = dto.getCalories();
        this.carbs = dto.getCarbs();
        this.protein = dto.getProtein();
        this.fat = dto.getFat();
        this.sugar = dto.getSugar();
        this.sodium = dto.getSodium();
        this.nAmount = dto.getNAmount();
        this.dish = dto.getDish();
        this.ingredient = dto.getIngredient();
    }
}
