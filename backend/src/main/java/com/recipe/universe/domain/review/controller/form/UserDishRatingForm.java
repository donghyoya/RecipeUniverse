package com.recipe.universe.domain.review.controller.form;

import lombok.Data;

@Data
public class UserDishRatingForm {
    private Long dishId;
    private Double rating;
    private String review;
}
