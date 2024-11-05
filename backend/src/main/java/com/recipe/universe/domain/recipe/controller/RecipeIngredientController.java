package com.recipe.universe.domain.recipe.controller;

import com.recipe.universe.domain.ingredient.dto.ReadIngredientDto;
import com.recipe.universe.domain.ingredient.service.IngredientService;
import com.recipe.universe.global.dto.BaseListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "레시피")
@RequiredArgsConstructor
@RestController
@RequestMapping("/recipe/ing")
public class RecipeIngredientController {
    private final IngredientService ingredientService;

    @Operation(summary = "재료 검색 API", description = "재료 생성시 id는 이 api를 사용하여 검색할 것")
    @GetMapping("/{queryString}")
    public BaseListResponse<ReadIngredientDto> queryByName(
            @Parameter(name = "재료 검색")
            @PathVariable("queryString") String queryString
    ){
        return new BaseListResponse<>(ingredientService.findByLikeIngName(queryString));
    }

}
