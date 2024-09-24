package com.recipe.universe.domain.dish.dish.service;

import com.recipe.universe.domain.dish.controller.form.GeneralRecipeForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import com.recipe.universe.domain.dish.recipe.service.RecipeService;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import com.recipe.universe.domain.user.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RecipeService recipeService;
    private final UserRepository userRepository;

    /* CREATE */

    @Transactional
    public Long createDish(
            Long userId,
            String dishName, String description,
            Integer preparationTime, Integer cookingTime,
            Integer servingSize, Integer recipeLevel,
            List<GeneralRecipeForm> recipes){
        User user = userRepository.findById(userId).orElseThrow();
        Dish dish = Dish.builder()
                .dishName(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .cokkingTime(cookingTime)
                .servingSize(servingSize)
                .recipeLevel(recipeLevel)
                .user(user)
                .build();
        Long id = dishRepository.save(dish).getId();

        /* 레시피 추가 */
        for(GeneralRecipeForm recipe : recipes){
            recipeService.createRecipe(
                recipe.getRecipeNum(),
                recipe.getDescription(),
                dish
            );
        }
        return id;
    }

    /* READ */

    public DishDto findById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow();
        return DishDto.convert(dish);
    }

    public DishWithRecipeDto findDishWithRecipeById(Long id){
        DishDto dish = findById(id);
        List<RecipeDto> recipes = recipeService.findRecipeByDishId(id);
        return new DishWithRecipeDto(dish, recipes);
    }

    public List<DishDto> findAllDish(){
        return dishRepository.findAll().stream().map(DishDto::convert).toList();
    }

    /* UPDATE */


    /* DELETE */

    @Transactional
    public void deleteById(Long id){
        dishRepository.deleteById(id);
    }

}
