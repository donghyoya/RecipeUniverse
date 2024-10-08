package com.recipe.universe.domain.recipe.recipe.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient;
import com.recipe.universe.domain.recipe.step.entity.RecipeStep;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.review.entity.UserReview;
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
public class Recipe extends BaseEntity {
    /**
     * ID
     */
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    /* ---속성--- */

    /**
     * 요리이름
     */
    @Column
    private String name;

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
     * 총 요리시간
     * 주의 이 column은 반정규화가 적용된 column입니다.
     */
    @Column
    private Integer cookingTime = 0;

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
        this.user.addRecipes(this);
    }

    /* DishIngredient */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public void addDishIngredient(RecipeIngredient recipeIngredient){
        this.recipeIngredients.add(recipeIngredient);
    }

    /* R - Recipe */

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeStep> steps = new ArrayList<>();

    public void addSteps(RecipeStep step){
        steps.add(step);
        this.cookingTime += step.getCookingTime();
    }

    /* R - Rating */

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserReview> ratings = new ArrayList<>();

    public void addRatings(UserReview rating){
        ratings.add(rating);
    }

    /* 좋아요 관련 */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLike> likes = new ArrayList<>();

    public void addLike(UserLike like){
        likes.add(like);
    }

    /* LOGIC */

    public void update(
            String name,
            String description,
            String cuisineType,
            String mealType,
            Integer preparationTime,
            Integer servingSize,
            Integer recipeLevel,
            String dishCategory
    ){
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.servingSize = servingSize;
        this.recipeLevel = recipeLevel;
        this.dishCategory = dishCategory;
    }

    public void updateCookingTime(Integer beforeCookingTime, Integer aftercookingTime) {
        this.cookingTime = this.cookingTime - beforeCookingTime + aftercookingTime;
    }

    /* 생성 */

    public Recipe(String name, String description, String cuisineType, String mealType, Integer preparationTime, Integer servingSize, Integer recipeLevel, String dishCategory, User user) {
        this.name = name;
        this.description = description;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.preparationTime = preparationTime;
        this.servingSize = servingSize;
        this.recipeLevel = recipeLevel;
        this.dishCategory = dishCategory;
        addUser(user);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String name;
        private String description;
        private String cuisineType;
        private String mealType;
        private Integer preparationTime;
        private Integer servingSize;
        private Integer recipeLevel;
        private String dishCategory;
        private User user;

        public Recipe build(){
            return new Recipe(
                    name,
                    description,
                    cuisineType,
                    mealType,
                    preparationTime,
                    servingSize,
                    recipeLevel,
                    dishCategory,
                    user
            );
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder servingSize(Integer servingSize) {
            this.servingSize = servingSize;
            return this;
        }

        public Builder recipeLevel(Integer recipeLevel) {
            this.recipeLevel = recipeLevel;
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
