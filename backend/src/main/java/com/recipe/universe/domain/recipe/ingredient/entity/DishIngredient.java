package com.recipe.universe.domain.recipe.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Dish;
import com.recipe.universe.domain.ingredient.entity.Ingredient;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column
    private Boolean optional;

    @Column
    private String description;

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

    public DishIngredient(Dish dish, Ingredient ingredient) {
        addDish(dish);
        addIngredeint(ingredient);
    }

    public DishIngredient(Double dAmount, String unit, Boolean optional, String description, Dish dish, Ingredient ingredient) {
        this(dish, ingredient);
        this.dAmount = dAmount;
        this.unit = unit;
        this.optional = optional;
        this.description = description;
        this.dish = dish;
        this.ingredient = ingredient;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void update(Double amount, String unit, String description, Boolean optional) {
        this.dAmount =amount;
        this.unit = unit;
        this.description = description;
        this.optional = optional;
    }

    public static class Builder{
        private Double dAmount;
        private String unit;
        private Boolean optional;
        private String description;
        private Dish dish;
        private Ingredient ingredient;

        public DishIngredient build(){
            return new DishIngredient(dAmount, unit, optional, description, dish, ingredient);
        }

        public Builder dAmount(Double dAmount) {
            this.dAmount = dAmount;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder optional(Boolean optional) {
            this.optional = optional;
            if(optional == null){
                this.optional = false;
            }
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            if(description == null){
                this.description = "";
            }
            return this;
        }

        public Builder dish(Dish dish) {
            this.dish = dish;
            return this;
        }

        public Builder ingredient(Ingredient ingredient) {
            this.ingredient = ingredient;
            if(this.unit == null){
                this.unit = ingredient.getUnit();
            }
            return this;
        }
    }

}
