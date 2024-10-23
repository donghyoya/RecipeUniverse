package com.recipe.universe.domain.recipe.recipe.dto;

import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeWithHashTagDto extends RecipeDto{
    private List<String> hashtags;

    RecipeWithHashTagDto(RecipeDto recipe){
        super(
                recipe.getId(), recipe.getName(), recipe.getDescription(), recipe.getCuisineType(), recipe.getMealType(), recipe.getPreparationTime(), recipe.getCookingTime(), recipe.getServingSize(), recipe.getDifficulty(), recipe.getDishCategory()
        );
    }

    public RecipeWithHashTagDto(Recipe recipe){
        this(RecipeDto.convert(recipe));
        hashtags = recipe.getRecipeHashTags().stream().map(rt->rt.getHashTag().getTagname()).collect(Collectors.toList());
    }
}
