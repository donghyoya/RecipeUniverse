package com.recipe.universe.domain.recipe.recipe.dto;


import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeDto {
    private Long id;

    /**
     * 요리이름
     */
    private String name;

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
    private Integer difficulty;

    /**
     * 요리 카테고리
     */
    private String dishCategory;

    /**
     * 좋아요 수
     */
    private int likes;

    public static RecipeDto convert(Recipe recipe){
        return builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .preparationTime(recipe.getPreparationTime())
                .cookingTime(recipe.getCookingTime())
                .servingSize(recipe.getServingSize())
                .difficulty(recipe.getDifficulty())
                .likes(recipe.getLikes().size())
                .build();
    }
}
