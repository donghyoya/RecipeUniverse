package com.recipe.universe.domain.review.dto;

import com.recipe.universe.domain.review.entity.UserReview;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserReviewDto {
    @Schema(description = "리뷰의 id")
    private Long id;
    @Schema(description = "리뷰의 평점", example = "1~5")
    private Double rating;
    @Schema(description = "리뷰의 내용")
    private String review;
    @Schema(description = "작성한 user의 id")
    private Long userId;
    @Schema(description = "리뷰가 작성된 레시피 id")
    private Long recipeId;
    @Schema(description = "리뷰의 좋아요 수")
    private int likes;

    public UserReviewDto(UserReview review){
        this.id = review.getId();
        this.rating = review.getRating();
        this.review = review.getReview();
        this.userId = review.getUserId();
        this.recipeId = review.getRecipeId();
        this.likes = review.getLikes().size();
    }
}
