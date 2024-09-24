package com.recipe.universe.domain.rating.controller.form;

import lombok.Data;

@Data
public class UserDishRatingForm {
    private Long dishId;
    private Double rating;
    private String review;
}
