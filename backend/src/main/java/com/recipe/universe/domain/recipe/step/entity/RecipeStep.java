package com.recipe.universe.domain.recipe.step.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Dish;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@SQLRestriction("del_flag = false")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecipeStep extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "recipe_step_id")
    private Long id;

    /**
     * 조리법 번호
     */
    @Column(name = "recipe_step_order")
    private Long order;

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
        dish.addSteps(this);
        this.dish = dish;
    }

    public void updateRecipe(Long order, String description){
        this.order = order;
        this.description = description;
    }

    public RecipeStep(Long order, String description, Dish dish) {
        this.order = order;
        this.description = description;
        addDish(dish);
    }
}
