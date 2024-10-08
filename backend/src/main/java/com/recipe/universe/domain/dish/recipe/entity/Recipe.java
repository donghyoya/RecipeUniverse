package com.recipe.universe.domain.dish.recipe.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@SQLRestriction("del_flag = false")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recipe extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    /**
     * 조리법 번호
     */
    @Column
    private Long recipeNum;

    /**
     * 조리법 설명
     */
    @Column
    private String description;

    @Column(name = "dish_id", insertable = false, updatable = false)
    private Long dishId;

    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private void addDish(Dish dish){
        dish.addRecipes(this);
        this.dish = dish;
    }

    public void updateRecipe(Long recipeNum, String description){
        this.recipeNum = recipeNum;
        this.description = description;
    }

    public Recipe(Long recipeNum, String description, Dish dish) {
        this.recipeNum = recipeNum;
        this.description = description;
        addDish(dish);
    }
}
