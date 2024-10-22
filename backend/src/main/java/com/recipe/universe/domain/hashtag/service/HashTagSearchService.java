package com.recipe.universe.domain.hashtag.service;

import com.recipe.universe.domain.hashtag.entity.HashTag;
import com.recipe.universe.domain.hashtag.repository.HashTagRepository;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final RecipeRepository recipeRepository;

    public Page<RecipeDto> findByTagname(String tagname, int page, int size){
        Optional<HashTag> opt = hashTagRepository.findRecipeHashTagByTagname(tagname);
        if(opt.isEmpty()){
            return Page.empty();
        }else {
            List<Long> recipeIds = opt.get().getRecipeHashTags().stream().map(t -> t.getRecipeId()).collect(Collectors.toList());
            return recipeRepository.findByIdIn(recipeIds, PageRequest.of(page, size)).map(RecipeDto::convert);
        }
    }
}
