package com.recipe.universe.domain.user.user.service;

import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MyPageService {
    private final RecipeQueryRepository recipeQueryRepository;

    public Page<RecipeSearchDto> findMyPageRecipes(Long userId, int page, int size){
        return recipeQueryRepository.findByUserId(userId, PageRequest.of(page,size));
    }
}
