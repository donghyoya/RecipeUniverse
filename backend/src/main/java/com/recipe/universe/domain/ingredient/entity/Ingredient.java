package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingId;

    @Column(name = "ingredientName")
    private String ingName;         //재료명

    @Column(name = "category")
    private String category;        //카테고리(식품군)

    @Column(name = "unit")
    private String unit;            //단위

    /* DishIngredient */
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        recipeIngredients.add(recipeIngredient);
    }

    @Builder
    public Ingredient(String ingName, String category, String unit){
        this.ingName = ingName;
        this.category = category;
        this.unit = unit;
    }

    public Ingredient(CreateIngredientDto dto){
        this.ingName = dto.getIngredientName();
        this.category = dto.getCategory();
        this.unit = dto.getUnit();
    }
}
