package com.recipe.universe.domain.dish.dish.service;

import com.recipe.universe.domain.dish.controller.form.ingredient.CreateDishIngredientForm;
import com.recipe.universe.domain.dish.controller.form.recipe.GeneralRecipeForm;
import com.recipe.universe.domain.dish.controller.form.dish.UpdateDishForm;
import com.recipe.universe.domain.dish.controller.form.recipe.UpdateRecipeForm;
import com.recipe.universe.domain.dish.dish.dto.DishDto;
import com.recipe.universe.domain.dish.dish.dto.DishWithRecipeDto;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.dish.dish.repository.DishRepository;
import com.recipe.universe.domain.dish.ingredient.service.DishIngredientService;
import com.recipe.universe.domain.dish.recipe.dto.RecipeDto;
import com.recipe.universe.domain.dish.recipe.service.RecipeService;
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
    private final DishRepository dishRepository;
    private final RecipeService recipeService;
    private final UserRepository userRepository;
    private final DishIngredientService dishIngredientService;

    /* CREATE */

    @Transactional
    public Long createDish(
            Long userId,
            String dishName, String description,
            Integer preparationTime, Integer cookingTime,
            Integer servingSize, Integer recipeLevel,
            List<GeneralRecipeForm> recipes,
            List<CreateDishIngredientForm> ingredients){
        User user = userRepository.findById(userId).orElseThrow();
        Dish dish = Dish.builder()
                .dishName(dishName)
                .description(description)
                .preparationTime(preparationTime)
                .cookingTime(cookingTime)
                .servingSize(servingSize)
                .recipeLevel(recipeLevel)
                .user(user)
                .build();
        Long id = dishRepository.save(dish).getId();

        /* 레시피 추가 */
        for(GeneralRecipeForm recipe : recipes){
            recipeService.createRecipe(
                recipe.getRecipeNum(),
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
        Dish dish = dishRepository.findById(id).orElseThrow();
        return DishDto.convert(dish);
    }

    public DishWithRecipeDto findDishWithRecipeById(Long id){
        DishDto dish = findById(id);
        List<RecipeDto> recipes = recipeService.findRecipeByDishId(id);
        return new DishWithRecipeDto(dish, recipes);
    }

    public List<DishDto> findAllDish(){
        return dishRepository.findAll().stream().map(DishDto::convert).toList();
    }

    public List<DishDto> findByUserId(Long id){
        return dishRepository.findByUserId(id).stream().map(DishDto::convert).toList();
    }

    /* UPDATE */

    @Transactional
    public void updateDish(Long id, UpdateDishForm form){
        Dish dish = dishRepository.findById(id).orElseThrow();
        dish.update(
                form.getDishName(),
                form.getDescription(),
                form.getCuisineType(),
                form.getMealType(),
                form.getPreparationTime(),
                form.getCookingTime(),
                form.getServingSize(),
                form.getRecipeLevel(),
                form.getIngredientsCnt(),
                form.getDishCategory()
        );
        updateRecipe(form.getRecipes(), dish);
    }

    @Transactional
    public void updateRecipe(List<UpdateRecipeForm> forms, Dish dish){
        for(UpdateRecipeForm form : forms){
            if(form.isCreate()){
                recipeService.createRecipe(
                        form.getData().getRecipeNum(),
                        form.getData().getDescription(),
                        dish
                );
            }else if(form.isDelete()){
                recipeService.deleteRecipe(form.getId());
            }else {
                recipeService.updateRecipe(
                        form.getId(),
                        form.getData().getRecipeNum(),
                        form.getData().getDescription()
                );
            }
        }
    }

    /* DELETE */

    @Transactional
    public void deleteById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow();
        dish.delete();
    }

}
