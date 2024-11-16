package com.recipe.universe.domain.recipe.recipe.repository;

import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateRecipeIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithHashTagDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import com.recipe.universe.domain.recipe.recipe.service.RecipeService;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import com.recipe.universe.domain.user.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class RecipeQueryRepositoryTest {
    @Autowired private RecipeService recipeService;
    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private RecipeQueryRepository recipeQueryRepository;

    Long userId;
    List<GeneralStepForm> steps = new ArrayList<>();
    List<CreateRecipeIngredientForm> ingredients = new ArrayList<>();
    List<String> tagnames = new ArrayList<>();
    List<Long> recipeIds = new ArrayList<>();

    public RecipeQueryRepositoryTest() {
        tagnames.add("123");
        tagnames.add("456");
        tagnames.add("789");
    }

    @BeforeEach
    void setUp() {
        List<User> all = userRepository.findAll();
        if (!all.isEmpty()){
            userId = all.get(0).getId();
        }
        recipeRepository.deleteAll();
        for(int i=0;i<3;i++){
            Long recipeId = recipeService.createRecipe(userId,
                    "", "", 0, 0, RecipeDifficulty.Easy,
                    steps, ingredients, tagnames
            );
            recipeIds.add(recipeId);
        }
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteAll();
        recipeIds = new ArrayList<>();
    }

    @Test
    void findAllRecipe() {
        Page<Recipe> allRecipe = recipeQueryRepository.findAllRecipe(PageRequest.of(0, 15));
        assertEquals(recipeIds.size(), allRecipe.getNumberOfElements());
    }


}