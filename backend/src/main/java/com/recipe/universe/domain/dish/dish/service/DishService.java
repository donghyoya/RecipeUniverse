package com.recipe.universe.domain.dish.dish.service;

import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
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

    @Transactional
    public Long createDish(
            String dishName, String description,
            Integer preparationTime, Integer cookingTime,
            Integer servingSize, Integer recipeLevel){
        Dish dish = Dish.builder()
                .dishName(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .cokkingTime(cookingTime)
                .servingSize(servingSize)
                .recipeLevel(recipeLevel)
                .build();
        Long id = dishRepository.save(dish).getId();
        return id;
    }

    public DishDto findById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow();
        return DishDto.convert(dish);
    }

    public void deleteById(Long id){
        dishRepository.deleteById(id);
    }
}
