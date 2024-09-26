package com.recipe.universe.domain.ingredient.service;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import com.recipe.universe.domain.nutrition.entity.Nutrition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Transactional
    public Long save(CreateIngredientDto dto){
        Ingredient ingredient = new Ingredient(dto);

        Long ingId = ingredientRepository.save(ingredient).getIngId();
        return ingId;
    }

    @Transactional
    public Long saveWithNutrition(CreateIngredientDto ingredientDto, CreateNutritionDto nutritionDto){

        //재료 입력후
        Ingredient ingredient = new Ingredient(ingredientDto);
        Ingredient afIngredient = ingredientRepository.save(ingredient);

        //재료 영양정보 저장
        nutritionDto.setIngredient(afIngredient);
        Nutrition nutrition = new Nutrition(nutritionDto);

        return afIngredient.getIngId();
    }

    /*READ*/

    /**
     * 제료 이름 검색 함수
     * @param ingName 제료이름
     * @return dto
     */
    public ReadIngredientDto findByIngName(String ingName){
        Ingredient ingredient = ingredientRepository.findByIngName(ingName).orElseThrow();
        ReadIngredientDto dto = new ReadIngredientDto(ingredient);
        return dto;
    }
}
