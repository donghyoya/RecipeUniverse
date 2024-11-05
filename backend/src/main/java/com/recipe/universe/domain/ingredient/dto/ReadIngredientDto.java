package com.recipe.universe.domain.ingredient.dto;

import com.recipe.universe.domain.ingredient.entity.Ingredient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadIngredientDto {
    @Schema(name = "재료 이름")
    public String ingredientName;
    @Schema(name = "재료 카테고리")
    public String category;

    public ReadIngredientDto(Ingredient ingredient){
        this.ingredientName = ingredient.getIngName();
        this.category = ingredient.getCategory();
    }
}
