package com.recipe.universe.domain.rating.service;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.rating.dto.UserDishRatingsDto;
import com.recipe.universe.domain.rating.entity.UserDishRatings;
import com.recipe.universe.domain.rating.repository.UserDishRatingsRepository;
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
    private final UserDishRatingsRepository ratingsRepository;
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
        UserDishRatings userDishRatings = new UserDishRatings(rating, review, user, recipe);
        Long id = ratingsRepository.save(userDishRatings).getId();
        return id;
    }

    /* READ */

    public UserDishRatingsDto findById(Long id){
        UserDishRatings userDishRatings = ratingsRepository.findById(id).orElseThrow();
        return new UserDishRatingsDto(userDishRatings);
    }

    public List<UserDishRatingsDto> findByUserId(Long id){
        return ratingsRepository.findByUserId(id).stream().map(UserDishRatingsDto::new).toList();
    }

    public List<UserDishRatingsDto> findByDishId(Long id){
        return ratingsRepository.findByDishId(id).stream().map(UserDishRatingsDto::new).toList();
    }

    /* UPDATE */

    @Transactional
    public UserDishRatingsDto updateRating(Long id, Double rating, String review){
        UserDishRatings ratings = ratingsRepository.findById(id).orElseThrow();
        ratings.update(rating,review);
        return new UserDishRatingsDto(ratings);
    }

    /* DELETE */
    @Transactional
    public void deleteById(Long id){
        UserDishRatings userDishRatings = ratingsRepository.findById(id).orElseThrow();
        userDishRatings.delete();
    }

}
