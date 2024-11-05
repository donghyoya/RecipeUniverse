package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.hashtag.entity.RecipeHashTag;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeWithHashTagDto extends RecipeDto{
    private Integer hashTagSize;
    @Schema(description = "해시태그목록")
    private List<String> hashtags;

    RecipeWithHashTagDto(RecipeDto recipe){
        super(
                recipe.getId(), recipe.getName(), recipe.getDescription(), recipe.getCuisineType(), recipe.getMealType(), recipe.getPreparationTime(), recipe.getCookingTime(), recipe.getServingSize(), recipe.getDifficulty(), recipe.getDishCategory(), recipe.getLikes()
        );
    }

    public RecipeWithHashTagDto(Recipe recipe){
        this(RecipeDto.convert(recipe));
        hashTagSize = recipe.getRecipeHashTags().size();
        hashtags = recipe.getRecipeHashTags().stream().map(RecipeHashTag::getTagname).toList();
    }
}
