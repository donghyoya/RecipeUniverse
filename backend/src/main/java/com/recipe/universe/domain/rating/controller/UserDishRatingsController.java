package com.recipe.universe.domain.rating.controller;

import com.recipe.universe.domain.like.service.UserLikeService;
import com.recipe.universe.domain.rating.controller.form.UserDishRatingForm;
import com.recipe.universe.domain.rating.dto.UserDishRatingsDto;
import com.recipe.universe.domain.rating.service.UserDishRatingsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ratings")
@RequiredArgsConstructor
@RestController
public class UserDishRatingsController {
    private final UserDishRatingsService ratingsService;
    private final UserLikeService userLikeService;

    @SecurityRequirement(name = "JWT")
    @PostMapping()
    public UserDishRatingsDto createRatings(@RequestBody UserDishRatingForm form, Authentication authentication){
        Long ratingId = ratingsService.createRatings(
                form.getRating(), form.getReview(),
                Long.parseLong(authentication.getName()),
                form.getDishId()
        );
        return ratingsService.findById(ratingId);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> updateRatings(@PathVariable("id") Long id) {
        ratingsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/update")
    public UserDishRatingsDto updateRatings(@PathVariable("id") Long id, @RequestBody UserDishRatingForm form){
        return ratingsService.updateRating(id, form.getRating(), form.getReview());
    }

    /* 좋아요 */
    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeDish(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.likeRating(userId, id);
        return ResponseEntity.ok("like success");
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/{id}/unlike")
    public ResponseEntity<String> unlikeDish(@PathVariable("id") Long id, Authentication authentication){
        Long userId = Long.parseLong(authentication.getName());
        userLikeService.unlikeRating(userId, id);
        return ResponseEntity.ok("unlike success");
    }

}
