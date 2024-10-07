package com.recipe.universe.domain.review.service;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.review.repository.UserReviewRepository;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserReviewService {
    private final UserReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    /* CREATE */

    @Transactional
    public Long createReview(Double rating, String review, Long userId, Long dishId){
        User user = userRepository.findById(userId).orElseThrow();
        Recipe recipe = recipeRepository.findById(dishId).orElseThrow();
        return createReview(rating,review,user, recipe);
    }

    private Long createReview(Double rating, String review, User user, Recipe recipe){
        UserReview userReview = new UserReview(rating, review, user, recipe);
        Long id = reviewRepository.save(userReview).getId();
        return id;
    }

    /* READ */

    public UserReviewDto findById(Long id){
        UserReview userReview = reviewRepository.findById(id).orElseThrow();
        return new UserReviewDto(userReview);
    }

    public Page<UserReviewDto> findByUserId(Long id, int page, int size){
        return reviewRepository.findByUserId(id, PageRequest.of(page, size)).map(UserReviewDto::new);
    }

    public Page<UserReviewDto> findByRecipeId(Long id, int page, int size){
        return reviewRepository.findByRecipeId(id, PageRequest.of(page, size)).map(UserReviewDto::new);
    }

    /* UPDATE */

    @Transactional
    public UserReviewDto updateReview(Long id, Double rating, String review){
        UserReview targetReview = reviewRepository.findById(id).orElseThrow();
        targetReview.update(rating,review);
        return new UserReviewDto(targetReview);
    }

    /* DELETE */
    @Transactional
    public void deleteById(Long id){
        UserReview userReview = reviewRepository.findById(id).orElseThrow();
        userReview.delete();
    }

}
