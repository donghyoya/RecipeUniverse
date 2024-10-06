package com.recipe.universe.domain.recipe.recipe.service;

import com.recipe.universe.domain.recipe.controller.form.UpdateMethod;
import com.recipe.universe.domain.recipe.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.ingredient.UpdateDishIngredientForm;
import com.recipe.universe.domain.recipe.controller.form.step.GeneralStepForm;
import com.recipe.universe.domain.recipe.controller.form.dish.UpdateDishForm;
import com.recipe.universe.domain.recipe.controller.form.step.UpdateStepForm;
import com.recipe.universe.domain.recipe.recipe.dto.DishCompleteDto;
import com.recipe.universe.domain.recipe.recipe.dto.DishDto;
import com.recipe.universe.domain.recipe.recipe.dto.DishWithStepDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.repository.RecipeRepository;
import com.recipe.universe.domain.recipe.ingredient.dto.DishIngredientDto;
import com.recipe.universe.domain.recipe.ingredient.service.DishIngredientService;
import com.recipe.universe.domain.recipe.step.dto.RecipeStepDto;
import com.recipe.universe.domain.recipe.step.service.RecipeStepService;
import com.recipe.universe.domain.user.user.entity.User;
import com.recipe.universe.domain.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DishService {
    private final RecipeRepository recipeRepository;
    private final RecipeStepService recipeStepService;
    private final UserRepository userRepository;
    private final DishIngredientService dishIngredientService;

    /* CREATE */

    @Transactional
    public Long createDish(
            Long userId,
            String dishName, String description,
            Integer preparationTime, Integer cookingTime,
            Integer servingSize, Integer recipeLevel,
            List<GeneralStepForm> recipes,
            List<CreateDishIngredientForm> ingredients){
        User user = userRepository.findById(userId).orElseThrow();
        Recipe dish = Recipe.builder()
                .name(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .cookingTime(cookingTime)
                .servingSize(servingSize)
                .recipeLevel(recipeLevel)
                .user(user)
                .build();
        Long id = recipeRepository.save(dish).getId();

        /* 레시피 추가 */
        for(GeneralStepForm recipe : recipes){
            recipeStepService.createStep(
                recipe.getOrder(),
                recipe.getDescription(),
                dish
            );
        }

        /* 재료 추가 */
        for(CreateDishIngredientForm ingredient : ingredients){
            dishIngredientService.createDishIngredient(
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

    public DishDto findById(Long id){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        return DishDto.convert(recipe);
    }

    public DishWithStepDto findDishWithRecipeById(Long id){
        DishDto dish = findById(id);
        List<RecipeStepDto> recipes = recipeStepService.findStepByDishId(id);
        return new DishWithStepDto(dish, recipes);
    }

    public DishCompleteDto findDishComplete(Long id){
        Recipe recipe = recipeRepository.findDishWithRecipeById(id).orElseThrow();
        List<RecipeStepDto> recipes = recipe.getSteps().stream().map(RecipeStepDto::new).toList();
        List<DishIngredientDto> ingredients = dishIngredientService.findByDishId(id);
        return new DishCompleteDto(DishDto.convert(recipe), recipes, ingredients);
    }

    public List<DishDto> findAllDish(){
        return recipeRepository.findAll().stream().map(DishDto::convert).toList();
    }

    public List<DishDto> findByUserId(Long id){
        return recipeRepository.findByUserId(id).stream().map(DishDto::convert).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateDish(Long id, UpdateDishForm form){
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.update(
                form.getDishName(),
                form.getDescription(),
                form.getCuisineType(),
                form.getMealType(),
                form.getPreparationTime(),
                form.getCookingTime(),
                form.getServingSize(),
                form.getRecipeLevel(),
                form.getDishCategory()
        );
        updateRecipe(form.getSteps(), recipe);
        updateDishIngredient(form.getDishIngredients(), recipe);
    }

    private void updateRecipe(List<UpdateStepForm> forms, Recipe recipe){
        for(UpdateStepForm form : forms){
            if(form.getMethod() == UpdateMethod.CREATE){
                recipeStepService.createStep(
                        form.getData().getOrder(),
                        form.getData().getDescription(),
                        recipe
                );
            }else if(form.getMethod() == UpdateMethod.DELETE){
                recipeStepService.deleteStep(form.getId());
            }else {
                recipeStepService.updateStep(
                        form.getId(),
                        form.getData().getOrder(),
                        form.getData().getDescription()
                );
            }
        }
    }

    private void updateDishIngredient(List<UpdateDishIngredientForm> forms, Recipe recipe) {
        for(UpdateDishIngredientForm form : forms){
            if(form.getMethod() == UpdateMethod.UPDATE){
                dishIngredientService.createDishIngredient(
                        form.getAmount(),
                        form.getUnit(),
                        form.getDescription(),
                        form.getOptional(),
                        form.getIngredientName(),
                        recipe
                );
            }else if(form.getMethod() == UpdateMethod.DELETE){
                dishIngredientService.deleteById(form.getId());
            }else {
                dishIngredientService.update(
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
