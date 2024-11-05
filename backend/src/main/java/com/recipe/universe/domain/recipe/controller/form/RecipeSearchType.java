package com.recipe.universe.domain.recipe.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "레시피 검색유형")
public enum RecipeSearchType {
    @Schema(description = "태그 이름으로 검색")
    Tagname,
    @Schema(description = "레시피 이름으로 검색")
    RecipeName,
    @Schema(description = "검색하지 않음(최신순으로 받아옴)")
    None
}
