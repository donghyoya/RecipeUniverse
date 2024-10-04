package com.recipe.universe.domain.dish.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.dish.dish.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLRestriction("del_flag = false")
public class DishIngredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diId;

    @Column(name = "dishAmount")
    private Double dAmount;

    @Column
    private String unit;

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
        this.unit = this.ingredient.getUnit();
        ingredient.addDishIngredients(this);
    }

    /* 생성 */

    protected DishIngredient(){}

    public DishIngredient(Dish dish, Ingredient ingredient) {
        addDish(dish);
        addIngredeint(ingredient);
    }

    public DishIngredient(Double dAmount, Dish dish, Ingredient ingredient) {
        this.dAmount = dAmount;
        this.dish = dish;
        this.ingredient = ingredient;
    }

    public DishIngredient(Double dAmount, String unit, Dish dish, Ingredient ingredient) {
        this(dish, ingredient);
        this.dAmount = dAmount;
        this.unit = unit; // custom 단위를 사용하는 것으로 간주함
    }
}
