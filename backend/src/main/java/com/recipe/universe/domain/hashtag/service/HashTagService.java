package com.recipe.universe.domain.hashtag.service;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.hashtag.entity.HashTag;
import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import com.recipe.universe.domain.hashtag.repository.HashTagRepository;
import com.recipe.universe.domain.hashtag.repository.RecipeHashTagRepository;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class HashTagService {
    private final HashTagRepository hashTagRepository;
    private final RecipeHashTagRepository recipeHashTagRepository;

    /* CREATE */

    private HashTag findOrCreateHashTag(String tagname){
        Optional<HashTag> opt = hashTagRepository.findByTagname(tagname);
        if(opt.isEmpty()){
            HashTag hashTag = new HashTag(tagname);
            hashTag = hashTagRepository.save(hashTag);
            return hashTag;
        }else {
            return opt.get();
        }
    }

    private RecipeHashTag createRecipeHashTag(HashTag hashTag, Recipe recipe){
        RecipeHashTag recipeHashTag = new RecipeHashTag(hashTag, recipe);
        recipeHashTagRepository.save(recipeHashTag);
        return recipeHashTag;
    }

    @Transactional
    public RecipeHashTag createRecipeHashTagByTagname(String tagname, Recipe recipe){
        HashTag hashTag = findOrCreateHashTag(tagname);
        RecipeHashTag recipeHashTag;
        recipeHashTag = createRecipeHashTag(hashTag, recipe);
        return recipeHashTag;
    }

    /* READ */

    /* UPDATE */

    /* DELETE */

    @Transactional
    public void deleteRecipeHashTagByTagname(String tagname, Long recipeId){
        Optional<RecipeHashTag> opt = recipeHashTagRepository.findByTagnameAndRecipeId(tagname, recipeId);
        opt.ifPresent(BaseEntity::delete);
    }
}
