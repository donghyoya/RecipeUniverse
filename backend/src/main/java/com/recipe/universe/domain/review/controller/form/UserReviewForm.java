package com.recipe.universe.domain.review.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserReviewForm {
    @Schema(description = "리뷰를 작성할 레시피의 id")
    private Long recipeId;
    @Schema(description = "리뷰 rating")
    private Double rating;
    @Schema(description = "리뷰 내용")
    private String review;
}
