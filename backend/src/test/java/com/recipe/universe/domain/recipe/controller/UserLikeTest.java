package com.recipe.universe.domain.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateRecipeIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.recipe.CreateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserLikeTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private ObjectMapper objectMapper;
    private Long recipeId;
    private String getToken(){
        UserDto cheatUser = userService.findCheatUser();
        return jwtTokenService.generateCheatToken(cheatUser);
    }

    @BeforeEach
    public void createRecipeBeforeEach() throws Exception {
        String token = getToken();

        CreateRecipeForm form = CreateRecipeForm.builder()
                .name("test-name")
                .description("test-desc")
                .cuisineType("test-ct")
                .mealType("test-mt")
                .preparationTime(500)
                .servingSize(123)
                .difficulty(3)
                .dishCategory("test-dc")
                .steps(new ArrayList<>(List.of(new GeneralStepForm[]{
                        new GeneralStepForm(1l, "1", 1),
                        new GeneralStepForm(2l, "2", 2),
                        new GeneralStepForm(3l, "3", 3),
                })))
                .ingredients(new ArrayList<>(
                        List.of(new CreateRecipeIngredientForm[]{
                                new CreateRecipeIngredientForm("재료이름", 5.0,"g",true,"재료설명"),
                                new CreateRecipeIngredientForm("재료이름", 5.0,"g",true,"재료설명"),
                                new CreateRecipeIngredientForm("재료이름", 5.0,"g",true,"재료설명"),
                                new CreateRecipeIngredientForm("재료이름", 5.0,"g",true,"재료설명")
                        })
                ))
                .tagnames(new ArrayList<>())
                .build();

        MvcResult response = mockMvc.perform(
                        post("/recipe")
                                .header("Authorization", "Bearer " + token)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isOk())
                .andReturn();
        RecipeCompleteDto resp = objectMapper.readValue(response.getResponse().getContentAsString(), RecipeCompleteDto.class);
        recipeId = resp.getRecipe().getId();
    }

    @Test
    public void likeRecipe() throws Exception{
        MvcResult response = mockMvc.perform(get(String.format("/recipe/%d", recipeId))).andReturn();
        RecipeCompleteDto respBeforeLike = objectMapper.readValue(response.getResponse().getContentAsString(), RecipeCompleteDto.class);

        // 좋아요 한 뒤 좋아요가 늘었는지 검사
        mockMvc.perform(get(String.format("/recipe/%d/like", recipeId)));
        response = mockMvc.perform(get(String.format("/recipe/%d", recipeId))).andReturn();
        RecipeCompleteDto respAfterLike = objectMapper.readValue(response.getResponse().getContentAsString(), RecipeCompleteDto.class);

        // 좋아요 취소 뒤 좋아요가 줄었는지 검사
        mockMvc.perform(get(String.format("/recipe/%d/unlike", recipeId)));
        response = mockMvc.perform(get(String.format("/recipe/%d", recipeId))).andReturn();
        RecipeCompleteDto respAfterUnLike = objectMapper.readValue(response.getResponse().getContentAsString(), RecipeCompleteDto.class);
    }

}
