package com.recipe.universe.domain.rating.service;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
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
    private final DishRepository dishRepository;

    /* CREATE */

    @Transactional
    public Long createRatings(Double rating, String review, Long userId, Long dishId){
        User user = userRepository.findById(userId).orElseThrow();
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        return createRatings(rating,review,user,dish);
    }

    @Transactional
    public Long createRatings(Double rating, String review, User user, Dish dish){
        UserDishRatings userDishRatings = new UserDishRatings(rating, review, user, dish);
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

    /* DELETE */

    public void deleteById(Long id){
        dishRepository.deleteById(id);
    }

}
