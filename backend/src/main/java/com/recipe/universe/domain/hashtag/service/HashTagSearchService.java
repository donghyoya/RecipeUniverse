package com.recipe.universe.domain.hashtag.service;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import com.recipe.universe.domain.hashtag.repository.HashTagRepository;
import com.recipe.universe.domain.hashtag.repository.RecipeHashTagRepository;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithHashTagDto;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class HashTagSearchService {
    private final HashTagRepository hashTagRepository;
    private final RecipeHashTagRepository recipeHashTagRepository;
    private final RecipeRepository recipeRepository;

    public Page<RecipeWithHashTagDto> findByTagname(String tagname, int page, int size){
        Page<RecipeHashTag> tags = recipeHashTagRepository.findByTagname(tagname, PageRequest.of(page, size));
        if(tags.isEmpty()){
            return Page.empty();
        }else {
            List<Long> recipeIds = new ArrayList<>();
            for (RecipeHashTag recipeHashTag : tags.getContent()) {
                Long recipeId = recipeHashTag.getRecipeId();
                recipeIds.add(recipeId);
            }
            List<RecipeWithHashTagDto> recipes = recipeRepository.findRecipeWithHashTagByIdIn(recipeIds).stream().map(RecipeWithHashTagDto::new).toList();
            return new PageImpl<>(
                    recipes,
                    tags.getPageable(),
                    tags.getTotalElements()
            );
        }
    }
}
