package com.recipe.universe.domain.dish.ingredient.entity;

import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@NoArgsConstructor
@Getter
@SQLRestriction("del_flag = false")
public class DishIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diId;

    @Column(name = "dishAmount")
    private Double dAmount;

    /* Dish */
    @Column(name = "dish_id", insertable = false, updatable = false)
    private Long dishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    private void addDish(Dish dish){
        this.dish = dish;
        dish.addDishIngredient(this);
    }

    /* Ingredient */

    @Column(name = "ingId", insertable = false, updatable = false)
    private Long ingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingId")
    private Ingredient ingredient;

    private void addIngredeint(Ingredient ingredient){
        this.ingredient = ingredient;
        ingredient.addDishIngredients(this);
    }

}
