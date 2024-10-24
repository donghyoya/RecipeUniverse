package com.recipe.universe.domain.hashtag.dto;


import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import lombok.Getter;

@Getter
public class RecipeHashTagDto {
    private Long id;
    private Long recipeId;
    private String tagname;

    public RecipeHashTagDto(Long id, Long recipeId, String tagname) {
        this.id = id;
        this.recipeId = recipeId;
        this.tagname = tagname;
    }

    public static RecipeHashTagDto from(RecipeHashTag entity){
        return new RecipeHashTagDto(
                entity.getId(),
                entity.getRecipeId(),
                entity.getTagname()
        );
    }
}
