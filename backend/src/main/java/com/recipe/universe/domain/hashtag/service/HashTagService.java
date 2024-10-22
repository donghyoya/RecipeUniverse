package com.recipe.universe.domain.hashtag.service;

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

    @Transactional
    public HashTag createHashTag(String tagname){
        HashTag hashTag = new HashTag(tagname);
        hashTagRepository.save(hashTag);
        return hashTag;
    }

    @Transactional
    public RecipeHashTag createRecipeHashTag(HashTag hashTag, Recipe recipe){
        RecipeHashTag recipeHashTag = new RecipeHashTag(hashTag, recipe);
        recipeHashTagRepository.save(recipeHashTag);
        return recipeHashTag;
    }

    @Transactional
    public RecipeHashTag createRecipeHashTagByTagname(String tagname, Recipe recipe){
        Optional<HashTag> byTagname = hashTagRepository.findByTagname(tagname);
        HashTag hashTag;
        if(byTagname.isEmpty()){
            hashTag = createHashTag(tagname);
        }else {
            hashTag = byTagname.get();
        }
        RecipeHashTag recipeHashTag = createRecipeHashTag(hashTag, recipe);
        return recipeHashTag;
    }

    /* READ */

    /* UPDATE */

    /* DELETE */
    @Transactional
    public void deleteRecipeHashTagById(Long id){
        recipeHashTagRepository.deleteById(id);
    }
}
