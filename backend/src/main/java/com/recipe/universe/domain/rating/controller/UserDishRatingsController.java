package com.recipe.universe.domain.rating.controller;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateRatings(@PathVariable("id") Long id) {
        ratingsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
