package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import lombok.Getter;

@Getter
public class RecipeSearchDto {
    private Long id;
    private String name;
    private RecipeDifficulty difficulty;
    private Integer servingSize;
    private Integer cookingTime;
    private Double avgRating;
    private Integer reviewSize;
    private Integer likeSize;

    public RecipeSearchDto(Long id, String name, RecipeDifficulty difficulty, Integer servingSize, Integer cookingTime, Double avgRating, Integer reviewSize, Integer likeSize) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.servingSize = servingSize;
        this.cookingTime = cookingTime;
        this.avgRating = avgRating;
        this.reviewSize = reviewSize;
        this.likeSize = likeSize;
    }
}
