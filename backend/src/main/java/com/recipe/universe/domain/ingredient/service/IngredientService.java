package com.recipe.universe.domain.ingredient.service;

import com.recipe.universe.domain.ingredient.dto.CreateIngredientDto;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import com.recipe.universe.domain.ingredient.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Long save(CreateIngredientDto dto){
        Ingredient ingredient = Ingredient.builder()
                .ingName(dto.getIngredientName())
                .category(dto.getCategory())
                .build();

        Long save = ingredientRepository.save(ingredient).getIngId();
        return save;
    }
}
