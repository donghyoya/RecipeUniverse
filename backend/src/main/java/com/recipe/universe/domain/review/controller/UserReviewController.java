package com.recipe.universe.domain.review.controller;

import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.review.controller.form.UserReviewForm;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.service.UserReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
@RequiredArgsConstructor
@RestController
public class UserReviewController {
    private final UserReviewService reviewService;
    private final UserLikeService userLikeService;

    @SecurityRequirement(name = "JWT")
    @PostMapping()
    public UserReviewDto createReview(@RequestBody UserReviewForm form, Authentication authentication){
        Long ratingId = reviewService.createReview(
                form.getRating(), form.getReview(),
                Long.parseLong(authentication.getName()),
                form.getRecipeId()
        );
        return reviewService.findById(ratingId);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> updateReview(@PathVariable("id") Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public UserReviewDto updateReview(@PathVariable("id") Long id, @RequestBody UserReviewForm form){
        return reviewService.updateReview(id, form.getRating(), form.getReview());
    }

    /* 좋아요 */
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeRecipe(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.likeRating(userId, id);
        return ResponseEntity.ok("like success");
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/unlike")
    public ResponseEntity<String> unlikeRecipe(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.unlikeRating(userId, id);
        return ResponseEntity.ok("unlike success");
    }

}
