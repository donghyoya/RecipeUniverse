package com.recipe.universe.domain.review.dto;

import com.recipe.universe.domain.review.entity.UserReview;
import lombok.Getter;

@Getter
public class UserReviewDto {
    private Long id;
    private Double rating;
    private String review;
    private Long userId;
    private Long dishId;
    private int likes;

    public UserReviewDto(UserReview review){
        this.id = review.getId();
        this.rating = review.getRating();
        this.review = review.getReview();
        this.userId = review.getUserId();
        this.dishId = review.getRecipeId();
        this.likes = review.getLikes().size();
    }
}
