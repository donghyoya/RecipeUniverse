package com.recipe.universe.domain.like.service;

import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.like.repository.UserLikeRepository;
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
public class UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final UserReviewRepository ratingsRepository;


    /* 공통 */

    private void like(UserLike userLike){
        if(userLike.isDelFlag()){
            userLike.restore();
        }
    }

    private void unlike(UserLike userLike){
        if(!userLike.isDelFlag()){
            userLike.delete();
        }
    }


    /* Dish */

    public List<RecipeDto> findUserLikeDish(Long id){
        return userLikeRepository.findDishByUserId(id).stream().map(userLike -> RecipeDto.convert(userLike.getRecipe())).toList();
    }

    @Transactional
    public void likeDish(Long userId, Long dishId){
        Boolean b = userLikeRepository.existsByUserIdAndRecipeId(userId, dishId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndRecipeId(userId, dishId);
            like(userLike);
        }else {
            createDishLike(userId, dishId);
        }
    }

    private void createDishLike(Long userId, Long dishId){
        User user = userRepository.findById(userId).orElseThrow();
        Recipe recipe = recipeRepository.findById(dishId).orElseThrow();
        UserLike userLike = new UserLike(user, recipe);
        userLikeRepository.save(userLike);
    }

    @Transactional
    public void unlikeDish(Long userId, Long dishId){
        Boolean b = userLikeRepository.existsByUserIdAndRecipeId(userId, dishId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndRecipeId(userId, dishId);
            unlike(userLike);
        }
    }

    /* Rating */

    public List<UserDishRatingsDto> findUserLikeRating(Long id){
        return userLikeRepository.findReviewByUserId(id).stream().map(userLike -> new UserDishRatingsDto(userLike.getReview())).toList();
    }

    @Transactional
    public void likeRating(Long userId, Long ratingId){
        Boolean b = userLikeRepository.existsByUserIdAndReviewId(userId, ratingId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndReviewId(userId, ratingId);
            like(userLike);
        }else {
            createRatingLike(userId, ratingId);
        }
    }

    private void createRatingLike(Long userId, Long ratingId){
        User user = userRepository.findById(userId).orElseThrow();
        UserReview rating = ratingsRepository.findById(ratingId).orElseThrow();
        UserLike userLike = new UserLike(user, rating);
        userLikeRepository.save(userLike);
    }

    @Transactional
    public void unlikeRating(Long userId, Long ratingId){
        Boolean b = userLikeRepository.existsByUserIdAndReviewId(userId, ratingId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndReviewId(userId, ratingId);
            unlike(userLike);
        }
    }
}
