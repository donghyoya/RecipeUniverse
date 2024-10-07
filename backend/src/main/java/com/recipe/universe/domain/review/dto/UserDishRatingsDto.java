package com.recipe.universe.domain.review.dto;

import com.recipe.universe.domain.review.entity.UserReview;
import lombok.Getter;

@Getter
public class UserDishRatingsDto {
    private Long id;
    private Double rating;
    private String review;
    private Long userId;
    private Long dishId;

    public UserDishRatingsDto(UserReview ratings){
        this.id = ratings.getId();
        this.rating = ratings.getRating();
        this.review = ratings.getReview();
        this.userId = ratings.getUserId();
        this.dishId = ratings.getRecipeId();
    }
}
