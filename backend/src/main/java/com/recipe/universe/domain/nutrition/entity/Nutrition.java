package com.recipe.universe.domain.nutrition.entity;

import com.recipe.universe.domain.recipe.recipe.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Setter
@SQLRestriction("del_flag = false")
@NoArgsConstructor
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid;

    private Double calories;    //kcal
    private Double carbs;       //탄수화물
    private Double protein;     //단백질
    private Double fat;         //지방
    private Double sugar;       //당류
    private Double sodium;      //나트륨
    private Double moisture;    //수분
    private Double potassium;   //칼륨
    private Double calcium;     //칼슘

    @Column(name = "nutritionAmount")
    private Double nAmount;

    @OneToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id")
    private Dish dish;

    @OneToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ingId")
    private Ingredient ingredient;

    @Builder
    public Nutrition(Double calories, Double carbs, Double protein, Double fat, Double sugar,
                     Double sodium, Double nAmount, Double moisture, Double potassium,
                     Double calcium,
                     Dish dish, Ingredient ingredient) {
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
        this.moisture = dto.getMoisture();
        this.potassium = dto.getPotassium();
        this.calcium = dto.getCalcium();
    }

}
