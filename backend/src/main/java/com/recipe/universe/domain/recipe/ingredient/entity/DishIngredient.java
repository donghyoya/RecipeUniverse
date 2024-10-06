package com.recipe.universe.domain.recipe.ingredient.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
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
    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;

    private void addRecipe(Recipe recipe){
        this.recipe = recipe;
        recipe.addDishIngredient(this);
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

    public DishIngredient(Recipe recipe, Ingredient ingredient) {
        addRecipe(recipe);
        addIngredeint(ingredient);
    }

    public DishIngredient(Double dAmount, String unit, Boolean optional, String description, Recipe recipe, Ingredient ingredient) {
        this(recipe, ingredient);
        this.dAmount = dAmount;
        this.unit = unit;
        this.optional = optional;
        this.description = description;
        this.recipe = recipe;
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
        private Recipe recipe;
        private Ingredient ingredient;

        public DishIngredient build(){
            return new DishIngredient(dAmount, unit, optional, description, recipe, ingredient);
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

        public Builder dish(Recipe recipe) {
            this.recipe = recipe;
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
