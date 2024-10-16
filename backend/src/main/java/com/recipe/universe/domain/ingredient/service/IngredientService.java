package com.recipe.universe.domain.ingredient.service;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import com.recipe.universe.domain.nutrition.dto.CreateNutritionDto;
import com.recipe.universe.domain.nutrition.entity.Nutrition;
import com.recipe.universe.domain.nutrition.repository.NutritionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final NutritionRepository nutritionRepository;

    /**
     * 재료 저장
     * @param dto
     * @return
     */
    @Transactional
    public Long save(CreateIngredientDto dto){
        Ingredient ingredient = new Ingredient(dto);

        Long ingId = ingredientRepository.save(ingredient).getIngId();
        return ingId;
    }

    /**
     * 재료, 영양정보 동시 저장
     * @param ingredientDto
     * @param nutritionDto
     * @return
     */
    @Transactional
    public Long saveWithNutrition(CreateIngredientDto ingredientDto, CreateNutritionDto nutritionDto){

        //재료 입력후
        Ingredient ingredient = new Ingredient(ingredientDto);
        Ingredient afIngredient = ingredientRepository.save(ingredient);

        //재료 영양정보 저장
        Nutrition nutrition = new Nutrition(nutritionDto);
        nutrition.setIngredient(afIngredient);

        nutritionRepository.save(nutrition);

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

    public List<ReadIngredientDto> findByLikeIngName(String ingName){
        return ingredientRepository.findByIngNameContaining(ingName).stream().map(ReadIngredientDto::new).toList();
    }
}
