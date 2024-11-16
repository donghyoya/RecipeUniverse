package com.recipe.universe.domain.recipe.recipe.dto;


import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeDto {
    @Schema(description = "레시피 id", example = "158")
    private Long id;

    @Schema(description = "레시피 이름", example = "158")
    private String name;

    @Schema(description = "레시피 소개", example = "이 레시피는 영국에서 시작되어...")
    private String description;

    @Schema(description = "음식 종류 소개", example = "???")
    private String cuisineType;

    @Schema(description = "요리 종류 소개", example = "???")
    private String mealType;

    @Schema(description = "준비시간", example = "1 (분단위)")
    private Integer preparationTime;

    @Schema(description = "요리시간", example = "1 (분단위)")
    private Integer cookingTime;

    @Schema(description = "몇 인분인가?", example = "1(인분)")
    private Integer servingSize;

    @Schema(description = "요리 난이도", example = "Easy, Normal, Hard")
    private RecipeDifficulty difficulty;

    @Schema(description = "요리 카테고리", example = "???")
    private String dishCategory;

    @Schema(description = "좋아요 수", example = "123")
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
