package com.recipe.universe.domain.ingredient.service;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
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
        Ingredient ingredient = Ingredient.builder()
                .ingName(dto.getIngredientName())
                .category(dto.getCategory())
                .unit(dto.getUnit())
                .build();

        Long save = ingredientRepository.save(ingredient).getIngId();
        return save;
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
