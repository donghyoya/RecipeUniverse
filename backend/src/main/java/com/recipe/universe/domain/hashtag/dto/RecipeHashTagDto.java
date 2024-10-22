package com.recipe.universe.domain.hashtag.dto;


import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import lombok.Getter;

@Getter
public class RecipeHashTagDto {
    private Long hashTagId;
    private Long recipeId;
    private HashTagDto hashTag;

    public RecipeHashTagDto(Long hashTagId, Long recipeId, HashTagDto hashTag) {
        this.hashTagId = hashTagId;
        this.recipeId = recipeId;
        this.hashTag = hashTag;
    }

    public static RecipeHashTagDto from(RecipeHashTag entity){
        return new RecipeHashTagDto(
                entity.getHashTagId(),
                entity.getRecipeId(),
                HashTagDto.from(entity.getHashTag())
        );
    }
}
