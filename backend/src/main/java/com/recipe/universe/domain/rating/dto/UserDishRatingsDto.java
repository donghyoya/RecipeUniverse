package com.recipe.universe.domain.rating.dto;

import com.recipe.universe.domain.rating.entity.UserDishRatings;
import lombok.Getter;

@Getter
public class UserDishRatingsDto {
    private Long id;
    private Double rating;
    private String review;
    private Long userId;
    private Long dishId;

    public UserDishRatingsDto(UserDishRatings ratings){
        this.id = ratings.getId();
        this.rating = ratings.getRating();
        this.review = ratings.getReview();
        this.userId = ratings.getUserId();
        this.dishId = ratings.getDishId();
    }
}
