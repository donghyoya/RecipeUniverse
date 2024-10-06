package com.recipe.universe.domain.like.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.rating.entity.UserDishRatings;
import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

// @SQLRestriction("del_flag = false")를 사용하지 말것
@Getter
@Entity
public class UserLike extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    /* User */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    private void addUser(User user){
        this.user = user;
        user.addLike(this);
    }

    /* Ratings */

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private UserDishRatings rating;

    @Column(name = "rating_id", updatable = false, insertable = false)
    private Long ratingId;

    private void addRating(UserDishRatings rating){
        this.rating = rating;
        rating.addLike(this);
    }

    /* Dish */

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;

    @Column(name = "recipe_id", updatable = false, insertable = false)
    private Long recipeId;

    private void addRecipe(Recipe recipe){
        this.recipe = recipe;
        recipe.addLike(this);
    }

    /* 생성 */
    protected  UserLike(){}

    private UserLike(User user) {
        addUser(user);
    }

    public UserLike(User user, Recipe recipe) {
        this(user);
        addRecipe(recipe);
    }

    public UserLike(User user, UserDishRatings rating) {
        this(user);
        addRating(rating);
    }
}
