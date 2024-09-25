package com.recipe.universe.domain.ingredient.dto;

import com.recipe.universe.domain.ingredient.entity.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadIngredientDto {

    public String ingredientName;
    public String category;

    public ReadIngredientDto(Ingredient ingredient){
        this.ingredientName = ingredient.getIngName();
        this.category = ingredient.getCategory();
    }
}
