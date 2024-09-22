package com.recipe.universe.domain.dish.recipe.entity;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recipe {
    @Id @GeneratedValue
    private Long id;

    /**
     * 조리법 번호
     */
    @Column
    private Long recipeNum;

    /**
     * 조리법 설명
     */
    @Column
    private String description;

    @ManyToOne
    private Dish dish;

    public Recipe(Long recipeNum, String description, Dish dish) {
        this.recipeNum = recipeNum;
        this.description = description;
        this.dish = dish;
    }
}
