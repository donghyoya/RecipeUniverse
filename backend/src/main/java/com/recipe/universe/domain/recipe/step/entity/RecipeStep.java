package com.recipe.universe.domain.recipe.step.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
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

    @Column
    private Integer cookingTime;

    /* 관계 */

    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;

    private void addRecipe(Recipe recipe){
        recipe.addSteps(this);
        this.recipe = recipe;
    }

    /* 비지니스 로직 */

    public void updateRecipeStep(Long order, String description, Integer cookingTime){
        this.order = order;
        this.description = description;
        this.recipe.updateCookingTime(this.cookingTime, cookingTime);
        this.cookingTime = cookingTime;
    }

    @Override
    public void delete() {
        this.recipe.updateCookingTime(this.cookingTime, 0);
        super.delete();
    }

    @Override
    public void restore() {
        this.recipe.updateCookingTime(0, this.cookingTime);
        super.restore();
    }

    /* 생성 */

    public RecipeStep(Long order, String description, Recipe recipe, Integer cookingTime) {
        this.order = order;
        this.description = description;
        this.cookingTime = cookingTime;
        addRecipe(recipe);
    }
}
