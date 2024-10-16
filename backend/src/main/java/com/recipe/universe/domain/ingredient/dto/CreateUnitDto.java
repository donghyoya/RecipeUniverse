package com.recipe.universe.domain.ingredient.dto;

import com.recipe.universe.domain.ingredient.entity.SUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUnitDto {

    private String name;
    private SUnit sUnit;

}
