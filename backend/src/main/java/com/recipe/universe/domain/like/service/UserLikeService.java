package com.recipe.universe.domain.like.service;

import com.recipe.universe.domain.like.dto.UserLikeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.like.repository.UserLikeRepository;
import com.recipe.universe.domain.review.dto.UserReviewDto;
import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.review.repository.UserReviewRepository;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserLikeService {
    private final UserLikeRepository userLikeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final UserReviewRepository reviewRepository;

    /* recipe */

    /**
     * userId와 recipeId를 바탕으로 좋아요 처리
     *  좋아요 정보가 있으면 토글
     *  없으면 생성
     * @param userId
     * @param recipeId
     * @return
     */
    @Transactional
    public UserLikeDto toggleRecipeUser(Long userId, Long recipeId){
        Optional<UserLike> opt = userLikeRepository.findByUserIdAndRecipeId(userId, recipeId);
        UserLike userLike = opt.get();
        if(opt.isEmpty()){
            User user = userRepository.findById(userId).orElseThrow();
            Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
            userLike = userLikeRepository.save(new UserLike(user, recipe));
        }else {
            userLike.toggle();
        }
        return new UserLikeDto(userLike.isLike(), userLikeRepository.countRecipeLike(recipeId));
    }

    public UserLikeDto getLikeRecipe(Long userId, Long recipeId){
        Optional<UserLike> opt = userLikeRepository.findByUserIdAndRecipeId(userId, recipeId);
        boolean isLike = false;
        if(!opt.isEmpty()){
            isLike = opt.get().isLike();
        }
        return new UserLikeDto(isLike, userLikeRepository.countRecipeLike(recipeId));
    }

    public UserLikeDto getLikeReview(Long userId, Long reviewId){
        Optional<UserLike> opt = userLikeRepository.findByUserIdAndReviewId(userId, reviewId);
        boolean isLike = false;
        if(!opt.isEmpty()){
            isLike = opt.get().isLike();
        }
        return new UserLikeDto(isLike, userLikeRepository.countReviewLike(reviewId));
    }


    /* Rating */

    public Page<RecipeDto> findUserLikeRecipe(Long id, int page, int size){
        return userLikeRepository.findRecipeByUserId(id, PageRequest.of(page, size)).map(userLike -> RecipeDto.convert(userLike.getRecipe()));
    }


    public Page<UserReviewDto> findUserLikeRating(Long id, int page, int size){
        return userLikeRepository.findReviewByUserId(id, PageRequest.of(page, size)).map(userLike -> new UserReviewDto(userLike.getReview()));
    }

    @Transactional
    public UserLikeDto toggleReviewUser(Long userId, Long reviewId){
        Optional<UserLike> opt = userLikeRepository.findByUserIdAndReviewId(userId, reviewId);
        UserLike userLike = null;
        if(opt.isEmpty()){
            User user = userRepository.findById(userId).orElseThrow();
            UserReview review = reviewRepository.findById(reviewId).orElseThrow();
            userLike = userLikeRepository.save(new UserLike(user, review));
        }else {
            userLike = opt.get();
            userLike.toggle();
        }
        return new UserLikeDto(userLike.isLike(), userLikeRepository.countReviewLike(reviewId));
    }
}
