package com.recipe.universe.domain.recipe.recipe.service;

import com.recipe.universe.domain.recipe.controller.form.UpdateMethod;
import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.ingredient.UpdateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.controller.form.recipe.UpdateRecipeForm;
import com.recipe.universe.domain.recipe.controller.form.step.UpdateStepForm;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeDto;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeWithStepDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.recipe.ingredient.dto.RecipeIngredientDto;
import com.recipe.universe.domain.recipe.ingredient.service.RecipeIngredientService;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.service.RecipeStepService;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeStepService recipeStepService;
    private final UserRepository userRepository;
    private final RecipeIngredientService recipeIngredientService;

    /* CREATE */

    @Transactional
    public Long createRecipe(
            Long userId,
            String dishName, String description,
            Integer preparationTime,
            Integer servingSize, Integer difficulty,
            List<GeneralStepForm> steps,
            List<CreateDishIngredientForm> ingredients){
        User user = userRepository.findById(userId).orElseThrow();
        Recipe dish = Recipe.builder()
                .name(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .servingSize(servingSize)
                .difficulty(difficulty)
                .user(user)
                .build();
        Long id = recipeRepository.save(dish).getId();

        /* 레시피 추가 */
        for(GeneralStepForm step : steps){
            recipeStepService.createStep(
                step.getOrder(),
                step.getDescription(),
                step.getCookingTime(),
                dish
            );
        }

        /* 재료 추가 */
        for(CreateDishIngredientForm ingredient : ingredients){
            recipeIngredientService.createRecipeIngredient(
                    ingredient.getAmount(),
                    ingredient.getUnit(),
                    ingredient.getDescription(),
                    ingredient.getOptional(),
                    ingredient.getIngredientName(),
                    dish
            );
        }

        return id;
    }


    /* READ */

    public RecipeDto findById(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        return RecipeDto.convert(recipe);
    }

    public RecipeWithStepDto findRecipeWithRecipeStepById(Long id){
        RecipeDto dish = findById(id);
        List<RecipeStepDto> recipes = recipeStepService.findStepByDishId(id);
        return new RecipeWithStepDto(dish, recipes);
    }

    public RecipeCompleteDto findRecipeComplete(Long id){
        Recipe recipe = recipeRepository.findRecipeWithStepById(id).orElseThrow();
        List<RecipeStepDto> recipes = recipe.getSteps().stream().map(RecipeStepDto::new).toList();
        List<RecipeIngredientDto> ingredients = recipeIngredientService.findByRecipeId(id);
        return new RecipeCompleteDto(RecipeDto.convert(recipe), recipes, ingredients);
    }

    public Page<RecipeDto> findAllRecipes(int page, int size){
        return recipeRepository.findAll(PageRequest.of(page,size)).map(RecipeDto::convert);
    }

    public Page<RecipeDto> findByUserId(Long id, int page, int size){
        return recipeRepository.findByUserId(id, PageRequest.of(page,size)).map(RecipeDto::convert);
    }

    /* UPDATE */

    @Transactional
    public void updateRecipe(Long id, UpdateRecipeForm form){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.update(
                form.getName(),
                form.getDescription(),
                form.getCuisineType(),
                form.getMealType(),
                form.getPreparationTime(),
                form.getServingSize(),
                form.getDifficulty(),
                form.getDishCategory()
        );
        updateRecipe(form.getSteps(), recipe);
        updateRecipeIngredient(form.getDishIngredients(), recipe);
    }

    private void updateRecipe(List<UpdateStepForm> forms, Recipe recipe){
        for(UpdateStepForm form : forms){
            if(form.getMethod() == UpdateMethod.CREATE){
                recipeStepService.createStep(
                        form.getData().getOrder(),
                        form.getData().getDescription(),
                        form.getData().getCookingTime(),
                        recipe
                );
            }else if(form.getMethod() == UpdateMethod.DELETE){
                recipeStepService.deleteStep(form.getId());
            }else {
                recipeStepService.updateStep(
                        form.getId(),
                        form.getData().getOrder(),
                        form.getData().getDescription(),
                        form.getData().getCookingTime()
                );
            }
        }
    }

    private void updateRecipeIngredient(List<UpdateDishIngredientForm> forms, Recipe recipe) {
        for(UpdateDishIngredientForm form : forms){
            if(form.getMethod() == UpdateMethod.UPDATE){
                recipeIngredientService.createRecipeIngredient(
                        form.getAmount(),
                        form.getUnit(),
                        form.getDescription(),
                        form.getOptional(),
                        form.getIngredientName(),
                        recipe
                );
            }else if(form.getMethod() == UpdateMethod.DELETE){
                recipeIngredientService.deleteById(form.getId());
            }else {
                recipeIngredientService.update(
                        form.getId(),
                        form.getAmount(),
                        form.getUnit(),
                        form.getDescription(),
                        form.getOptional()
                );
            }
        }
    }

    /* DELETE */

    @Transactional
    public void deleteById(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.delete();
    }

}
