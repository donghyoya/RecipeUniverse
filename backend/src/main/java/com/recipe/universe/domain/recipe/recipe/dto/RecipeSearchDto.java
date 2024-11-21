package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import com.recipe.universe.domain.user.user.dto.UserInfoDto;
import lombok.Getter;

/*
* 레시피 검색결과를 보여주는 DTO
* */
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
    private UserInfoDto user;

    public RecipeSearchDto(Long id, String name, RecipeDifficulty difficulty, Integer servingSize, Integer cookingTime, Double avgRating, Integer reviewSize, Integer likeSize, Long userId, String nickname) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.servingSize = servingSize;
        this.cookingTime = cookingTime;
        this.avgRating = avgRating;
        this.reviewSize = reviewSize;
        this.likeSize = likeSize;
        this.user = new UserInfoDto(userId, nickname);
    }
}
