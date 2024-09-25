package com.recipe.universe.domain.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ingredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingId;

    @Column(name = "ingredientName")
    private String ingName;

    @Column(name = "category")
    private String category;

    @Builder
    public Ingredient(String ingName, String category){
        this.ingName = ingName;
        this.category = category;
    }
}
