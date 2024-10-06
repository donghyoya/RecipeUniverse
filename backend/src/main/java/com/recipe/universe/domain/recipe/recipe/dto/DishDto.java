package com.recipe.universe.domain.recipe.recipe.dto;


import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DishDto {
    private Long id;

    /**
     * 요리이름
     */
    private String dishName;

    /**
     * 요리 설명
     */
    private String description;

    /**
     * 음식 문화 종류
     */
    private String cuisineType;

    /**
     * 요리종류
     */
    private String mealType;

    /**
     * 준비시간
     */
    private Integer preparationTime;

    /**
     * 요리시간
     */
    private Integer cookingTime;

    /**
     * 몇 인분
     */
    private Integer servingSize;

    /**
     * 난이도
     */
    private Integer recipeLevel;

    /**
     * 요리 카테고리
     */
    private String dishCategory;

    public static DishDto convert(Recipe recipe){
        return builder()
                .id(recipe.getId())
                .dishName(recipe.getName())
                .description(recipe.getDescription())
                .preparationTime(recipe.getPreparationTime())
                .cookingTime(recipe.getCookingTime())
                .servingSize(recipe.getServingSize())
                .recipeLevel(recipe.getRecipeLevel())
                .build();
    }
}
