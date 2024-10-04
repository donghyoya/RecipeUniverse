package com.recipe.universe.domain.dish.dish.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.dish.recipe.entity.Recipe;
import com.recipe.universe.domain.dish.ingredient.entity.DishIngredient;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.rating.entity.UserDishRatings;
import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@SQLRestriction("del_flag = false")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Dish extends BaseEntity {
    /**
     * ID
     */
    @Id @GeneratedValue
    private Long id;

    /* ---속성--- */

    /**
     * 요리이름
     */
    @Column
    private String dishName;

    /**
     * 요리 설명
     */
    @Column
    private String description;

    /**
     * 음식 문화 종류
     */
    @Column
    private String cuisineType;

    /**
     * 요리종류
     */
    @Column
    private String mealType;

    /**
     * 준비시간
     */
    @Column
    private Integer preparationTime;

    /**
     * 요리시간
     */
    @Column
    private Integer cookingTime;

    /**
     * 몇 인분
     */
    @Column
    private Integer servingSize;

    /**
     * 난이도
     */
    @Column
    private Integer recipeLevel;

    /**
     * 재료갯수
     */
    @Column
    private Integer integeringredientsCnt;

    /**
     * 요리 카테고리
     */
    @Column
    private String dishCategory;

    /* ---관계--- */

    /* USER */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public void addUser(User user){
        this.user = user;
        this.user.addDishes(this);
    }

    /* DishIngredient */
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishIngredient> dishIngredients = new ArrayList<>();

    public void addDishIngredient(DishIngredient dishIngredient){
        this.dishIngredients.add(dishIngredient);
    }

    /* R - Recipe */

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes;

    public void addRecipes(Recipe recipe){
        recipes.add(recipe);
    }

    /* R - Rating */

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserDishRatings> ratings;

    public void addRatings(UserDishRatings rating){
        ratings.add(rating);
    }

    /* 좋아요 관련 */
    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLike> likes;

    public void addLike(UserLike like){
        likes.add(like);
    }

    /* LOGIC */

    public void update(
            String dishName,
            String description,
            String cuisineType,
            String mealType,
            Integer preparationTime,
            Integer cookingTime,
            Integer servingSize,
            Integer recipeLevel,
            Integer integeringredientsCnt,
            String dishCategory
    ){
        this.dishName = dishName;
        this.description = description;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.servingSize = servingSize;
        this.recipeLevel = recipeLevel;
        this.integeringredientsCnt = integeringredientsCnt;
        this.dishCategory = dishCategory;
    }

    /* 생성 */

    public Dish(String dishName, String description, String cuisineType, String mealType, Integer preparationTime, Integer cookingTime, Integer servingSize, Integer recipeLevel, Integer integeringredientsCnt, String dishCategory, User user) {
        this.dishName = dishName;
        this.description = description;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.cookingTime = cookingTime;
        this.servingSize = servingSize;
        this.recipeLevel = recipeLevel;
        this.integeringredientsCnt = integeringredientsCnt;
        this.dishCategory = dishCategory;
        this.recipes = new ArrayList<>();
        this.ratings = new ArrayList<>();
        addUser(user);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String dishName;
        private String description;
        private String cuisineType;
        private String mealType;
        private Integer preparationTime;
        private Integer cookingTime;
        private Integer servingSize;
        private Integer recipeLevel;
        private Integer integeringredientsCnt;
        private String dishCategory;
        private User user;

        public Dish build(){
            return new Dish(
                    dishName,
                    description,
                    cuisineType,
                    mealType,
                    preparationTime,
                    cookingTime,
                    servingSize,
                    recipeLevel,
                    integeringredientsCnt,
                    dishCategory,
                    user
            );
        }

        public Builder dishName(String dishName) {
            this.dishName = dishName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder cuisineType(String cuisineType) {
            this.cuisineType = cuisineType;
            return this;
        }

        public Builder mealType(String mealType) {
            this.mealType = mealType;
            return this;
        }

        public Builder preparationTime(Integer preparationTime) {
            this.preparationTime = preparationTime;
            return this;
        }

        public Builder cookingTime(Integer cookingTime) {
            this.cookingTime = cookingTime;
            return this;
        }

        public Builder servingSize(Integer servingSize) {
            this.servingSize = servingSize;
            return this;
        }

        public Builder recipeLevel(Integer recipeLevel) {
            this.recipeLevel = recipeLevel;
            return this;
        }

        public Builder integeringredientsCnt(Integer integeringredientsCnt) {
            this.integeringredientsCnt = integeringredientsCnt;
            return this;
        }

        public Builder dishCategory(String dishCategory) {
            this.dishCategory = dishCategory;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }
    }

}
