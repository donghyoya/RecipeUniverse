package com.recipe.universe.domain.nutrition.service;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import com.recipe.universe.domain.nutrition.entity.Nutrition;
import com.recipe.universe.domain.nutrition.repository.NutritionRepository;
import com.recipe.universe.global.dto.NutritionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class NutritionService {

    private final NutritionRepository nutritionRepository;
    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;


    /**
     * 영양소 저장
     * @param typeId
     * @param type
     * @param nutritionDto
     * @return
     */
    @Transactional
    public Long save(Long typeId, NutritionType type, CreateNutritionDto nutritionDto){

        Ingredient ingredient = null;
        Dish dish = null;
        Nutrition nutrition = null;

        if(type.equals("DISH")){
            dish = dishRepository.getReferenceById(typeId);

            nutritionDto.setDish(dish);
            nutrition  = new Nutrition(nutritionDto);

        }else if(type.equals("ING")){
            ingredient = ingredientRepository.getReferenceById(typeId);

            nutritionDto.setIngredient(ingredient);
            nutrition = new Nutrition(nutritionDto);
        }

        Long nid = nutritionRepository.save(nutrition).getNid();

        return nid;
    }
}
