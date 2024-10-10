package com.recipe.universe.domain.review.controller.form;

import lombok.Data;

@Data
public class UserReviewForm {
    private Long recipeId;
    private Double rating;
    private String review;
}
