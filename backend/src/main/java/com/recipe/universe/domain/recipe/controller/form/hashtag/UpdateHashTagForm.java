package com.recipe.universe.domain.recipe.controller.form.hashtag;

import com.recipe.universe.domain.recipe.controller.form.UpdateMethod;
import lombok.Getter;

@Getter
public class UpdateHashTagForm {
    private UpdateMethod method;
    private String tagname;
}
