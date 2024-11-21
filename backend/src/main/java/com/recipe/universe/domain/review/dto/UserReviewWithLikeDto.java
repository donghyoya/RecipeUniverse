package com.recipe.universe.domain.review.dto;

import com.recipe.universe.domain.user.user.dto.UserInfoDto;
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
    @Schema(description = "리뷰가 작성된 레시피 id")
    private Long recipeId;
    @Schema(description = "리뷰의 좋아요 수")
    private Integer likes;
    @Schema(description = "리뷰 작성자 정보")
    private UserInfoDto user;

    public UserReviewWithLikeDto(Long id, Double rating, String review, Long userId, String nickname, Long recipeId, Integer likes) {
        this.id = id;
        this.rating = rating;
        this.review = review;
        this.recipeId = recipeId;
        this.likes = likes;
        this.user = new UserInfoDto(userId, nickname);
    }
}
