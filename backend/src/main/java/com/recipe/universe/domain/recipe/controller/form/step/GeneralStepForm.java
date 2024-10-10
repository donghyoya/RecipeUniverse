package com.recipe.universe.domain.recipe.controller.form.step;

import lombok.Data;

@Data
public class GeneralStepForm {
    private Long order;
    private String description;
    private Integer cookingTime;
}
