package com.recipe.universe.domain.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.universe.domain.recipe.controller.form.recipe.CreateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.user.jwt.service.JwtTokenService;
import com.recipe.universe.domain.user.user.dto.UserDto;
import com.recipe.universe.domain.user.user.service.UserService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

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

    @Test
    public void createRecipe() throws Exception {
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
                .ingredients(new ArrayList<>())
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
        assertEquals(
                form.getName(),resp.getRecipe().getName()
        );
        assertEquals(
                form.getIngredients().size(), resp.getIngredients().size()
        );
        assertEquals(
                form.getSteps().size(), resp.getStepSize()
        );
    }



}