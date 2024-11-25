package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.unit.entity.Unit;
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

//    @Column(name = "unit")
//    private String unit;            //단위

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
    private List<IngUnit> ingUnits = new ArrayList<>();

    /* DishIngredient */
    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public void addRecipeIngredient(RecipeIngredient recipeIngredient){
        recipeIngredients.add(recipeIngredient);
    }

    @Builder
    public Ingredient(String ingName, String category){
        this.ingName = ingName;
        this.category = category;
    }

    public Ingredient(CreateIngredientDto dto){
        this.ingName = dto.getIngredientName();
        this.category = dto.getCategory();
        List<IngUnit> ingUnits = new ArrayList<>();

        dto.getUnits().forEach(data ->{
            ingUnits.add(new IngUnit(data));
        });

        this.ingUnits = ingUnits;
    }

    public Ingredient(CreateIngredientDto dto, Unit unit){
        this.ingName = dto.getIngredientName();
        this.category = dto.getCategory();
        List<IngUnit> subIngUnits = new ArrayList<>();

        dto.getUnits().forEach(data ->{
            subIngUnits.add(new IngUnit(data));

        });

        this.ingUnits = subIngUnits;
    }

    public void addUnit(Unit unit){
        IngUnit ingUnit = new IngUnit();
        ingUnit.setUnit(unit);
        this.ingUnits.add(ingUnit);
        unit.getIngUnits().add(ingUnit);
    }
}
