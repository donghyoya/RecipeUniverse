package com.recipe.universe.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserReviewWithLikeDto {
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
    private Integer likes;

    public UserReviewWithLikeDto(Long id, Double rating, String review, Long userId, Long recipeId, Integer likes) {
        this.id = id;
        this.rating = rating;
        this.review = review;
        this.userId = userId;
        this.recipeId = recipeId;
        this.likes = likes;
    }
}
