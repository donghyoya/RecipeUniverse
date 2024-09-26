package com.recipe.universe.domain.nutrition.entity;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
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
}
