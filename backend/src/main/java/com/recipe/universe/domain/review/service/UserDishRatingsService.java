package com.recipe.universe.domain.review.service;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.review.dto.UserDishRatingsDto;
import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.review.repository.UserReviewRepository;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserDishRatingsService {
    private final UserReviewRepository ratingsRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    /* CREATE */

    @Transactional
    public Long createRatings(Double rating, String review, Long userId, Long dishId){
        User user = userRepository.findById(userId).orElseThrow();
        Recipe recipe = recipeRepository.findById(dishId).orElseThrow();
        return createRatings(rating,review,user, recipe);
    }

    @Transactional
    public Long createRatings(Double rating, String review, User user, Recipe recipe){
        UserReview userReview = new UserReview(rating, review, user, recipe);
        Long id = ratingsRepository.save(userReview).getId();
        return id;
    }

    /* READ */

    public UserDishRatingsDto findById(Long id){
        UserReview userReview = ratingsRepository.findById(id).orElseThrow();
        return new UserDishRatingsDto(userReview);
    }

    public List<UserDishRatingsDto> findByUserId(Long id){
        return ratingsRepository.findByUserId(id).stream().map(UserDishRatingsDto::new).toList();
    }

    public List<UserDishRatingsDto> findByDishId(Long id){
        return ratingsRepository.findByRecipeId(id).stream().map(UserDishRatingsDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public UserDishRatingsDto updateRating(Long id, Double rating, String review){
        UserReview ratings = ratingsRepository.findById(id).orElseThrow();
        ratings.update(rating,review);
        return new UserDishRatingsDto(ratings);
    }

    /* DELETE */
    @Transactional
    public void deleteById(Long id){
        UserReview userReview = ratingsRepository.findById(id).orElseThrow();
        userReview.delete();
    }

}
