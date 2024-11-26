package com.recipe.universe.domain.recipe.controller.form.step;

import lombok.Data;

@Data
public class GeneralStepForm {
    private Long order;
    private String description;
    private Integer cookingTime;

    public GeneralStepForm() {
    }

    public GeneralStepForm(Long order, String description, Integer cookingTime) {
        this.order = order;
        this.description = description;
        this.cookingTime = cookingTime;
    }
}