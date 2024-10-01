package com.recipe.universe.domain.like.service;

import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.dish.dish.service.DishService;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.like.repository.UserLikeRepository;
import com.recipe.universe.domain.rating.dto.UserDishRatingsDto;
import com.recipe.universe.domain.rating.entity.UserDishRatings;
import com.recipe.universe.domain.rating.repository.UserDishRatingsRepository;
import com.recipe.universe.domain.rating.service.UserDishRatingsService;
import com.recipe.universe.domain.user.user.dto.UserAndRoleDto;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import com.recipe.universe.domain.user.user.service.UserService;
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
    private final DishRepository dishRepository;
    private final UserDishRatingsRepository ratingsRepository;

    public List<DishDto> findUserLikeDish(Long id){
        return userLikeRepository.findDishByUserId(id).stream().map(userLike -> DishDto.convert(userLike.getDish())).toList();
    }

    @Transactional
    public void likeDish(Long userId, Long dishId){
        Boolean b = userLikeRepository.existsByUserIdAndDishId(userId, dishId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndRatingId(userId, dishId);
            like(userLike);
        }else {
            createDishLike(userId, dishId);
        }
    }

    @Transactional
    public void likeRating(Long userId, Long ratingId){
        Boolean b = userLikeRepository.existsByUserIdAndRatingId(userId, ratingId);
        if(b){
            UserLike userLike = userLikeRepository.findByUserIdAndRatingId(userId, ratingId);
            like(userLike);
        }else {
            createRatingLike(userId, ratingId);
        }
    }

    private void createRatingLike(Long userId, Long ratingId){
        User user = userRepository.findById(userId).orElseThrow();
        UserDishRatings rating = ratingsRepository.findById(ratingId).orElseThrow();
        UserLike userLike = new UserLike(user, rating);
        userLikeRepository.save(userLike);
    }

    private void createDishLike(Long userId, Long dishId){
        User user = userRepository.findById(userId).orElseThrow();
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        UserLike userLike = new UserLike(user, dish);
        userLikeRepository.save(userLike);
    }

    private void like(UserLike userLike){
        if(userLike.isDelFlag()){
            userLike.restore();
        }
    }

//    @Transactional
//    public void unlikeDish(Long userId, Long dishId){
//
//    }
//
//    @Transactional
//    public void unlikeRating(Long userId, Long ratingId){
//
//    }

}
