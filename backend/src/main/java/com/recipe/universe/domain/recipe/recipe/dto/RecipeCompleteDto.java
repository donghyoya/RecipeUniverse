package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.recipe.ingredient.dto.RecipeIngredientDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.view.RecipeSortView;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

/**
 * 레시피 + 좋아요 수 + 리뷰 수 + 평균리뷰 + 포함하는 해시태그 (이미지가 있다면 이미지까지 포함할 것)
 */
@NoArgsConstructor
@Getter
public class RecipeCompleteDto {
    @Schema(description = "레시피 정보")
    private RecipeDto recipe;
    private Integer likeCount;
    private Integer reviewSize;
    private Double avgRating;
    private List<String> hashtags;

    public RecipeCompleteDto(Recipe recipe, RecipeSortView view){
        this.recipe = RecipeDto.convert(recipe);
        this.hashtags = recipe.getRecipeHashTags().stream().map(RecipeHashTag::getTagname).toList();
        this.likeCount = view.getLikeCount();
        this.reviewSize = view.getReviewSize();
        this.avgRating = view.getAvgRating();
    }
}
