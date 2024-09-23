package com.recipe.universe.domain.dish.dish.service;

import com.recipe.universe.domain.dish.controller.form.GeneralRecipeForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import com.recipe.universe.domain.dish.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DishService {
    private final DishRepository dishRepository;
    private final RecipeService recipeService;

    @Transactional
    public Long createDish(
            String dishName, String description,
            Integer preparationTime, Integer cookingTime,
            Integer servingSize, Integer recipeLevel,
            List<GeneralRecipeForm> recipes){
        Dish dish = Dish.builder()
                .dishName(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .cokkingTime(cookingTime)
                .servingSize(servingSize)
                .recipeLevel(recipeLevel)
                .build();
        Long id = dishRepository.save(dish).getId();
        for(GeneralRecipeForm recipe : recipes){
            recipeService.createRecipe(
                recipe.getRecipeNum(),
                recipe.getDescription(),
                dish
            );
        }
        return id;
    }



    public DishDto findById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow();
        return DishDto.convert(dish);
    }

    public DishWithRecipeDto findDishWithRecipeById(Long id){
        DishDto dish = findById(id);
        List<RecipeDto> recipes = recipeService.findRecipeByDishId(id);
        return new DishWithRecipeDto(dish, recipes);
    }

    @Transactional
    public void deleteById(Long id){
        dishRepository.deleteById(id);
    }

    public List<DishDto> findAllDish(){
        return dishRepository.findAll().stream().map(DishDto::convert).toList();
    }
}
