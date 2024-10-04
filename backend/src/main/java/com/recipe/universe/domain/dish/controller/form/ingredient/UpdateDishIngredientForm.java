package com.recipe.universe.domain.dish.controller.form.ingredient;

import com.recipe.universe.domain.dish.controller.form.UpdateMethod;
import lombok.Data;

@Data
public class UpdateDishIngredientForm {
    private Long id;
    private UpdateMethod method;
    private String ingredientName;
    private Double amount;
    private String unit;
    private Boolean optional;
    private String description;

    public boolean isCreate(){
        return method.equals(UpdateMethod.CREATE);
    }

    public boolean isDelete(){
        return method.equals(UpdateMethod.DELETE);
    }
}
