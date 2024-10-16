package com.recipe.universe.domain.ingredient.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class CreateIngredientDto {

    public String ingredientName;
    public String category;
    public List<CreateIngUnitDto> units;

    public void setUnit(CreateIngUnitDto... unit){
        this.units = Arrays.asList(unit);
    }

}
