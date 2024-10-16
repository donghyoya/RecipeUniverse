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
public class RecipeIngredient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_ingredient_id")
    private Long id;

    @Column
    private Double amount;

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

    private void addIngredeint(Ingredient ingredient, String unit){
        this.ingredient = ingredient;
        this.unit = unit;
        ingredient.addRecipeIngredient(this);
    }

    /* 생성 */

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, String unit) {
        addRecipe(recipe);
        addIngredeint(ingredient, unit);
    }

    public RecipeIngredient(Double amount, String unit, Boolean optional, String description, Recipe recipe, Ingredient ingredient) {
        this(recipe, ingredient, unit);
        this.amount = amount;
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
        this.amount =amount;
        this.unit = unit;
        this.description = description;
        this.optional = optional;
    }

    public static class Builder{
        private Double amount;
        private String unit;
        private Boolean optional;
        private String description;
        private Recipe recipe;
        private Ingredient ingredient;

        public RecipeIngredient build(){
            return new RecipeIngredient(amount, unit, optional, description, recipe, ingredient);
        }

        public Builder amount(Double amount) {
            this.amount = amount;
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

        public Builder recipe(Recipe recipe) {
            this.recipe = recipe;
            return this;
        }

        public Builder ingredient(Ingredient ingredient) {
            this.ingredient = ingredient;
            if(this.unit == null){
                this.unit = ingredient.getUnits().get(0).getSUnit().name();
            }
            return this;
        }
    }

}
