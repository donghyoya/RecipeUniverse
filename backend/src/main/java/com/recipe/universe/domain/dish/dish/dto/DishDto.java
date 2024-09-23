package com.recipe.universe.domain.dish.dish.dto;


import com.recipe.universe.domain.dish.dish.entity.Dish;
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
    private Integer cokkingTime;

    /**
     * 몇 인분
     */
    private Integer servingSize;

    /**
     * 난이도
     */
    private Integer recipeLevel;

    /**
     * 재료갯수
     */
    private Integer integeringredientsCnt;

    /**
     * 요리 카테고리
     */
    private String dishCategory;

    public static DishDto convert(Dish dish){
        return builder()
                .id(dish.getId())
                .dishName(dish.getDishName())
                .description(dish.getDescription())
                .preparationTime(dish.getPreparationTime())
                .cokkingTime(dish.getCokkingTime())
                .servingSize(dish.getServingSize())
                .recipeLevel(dish.getRecipeLevel())
                .build();
    }
}