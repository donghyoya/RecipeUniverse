package com.recipe.universe.domain.recipe.recipe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipe is a Querydsl query type for Recipe
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipe extends EntityPathBase<Recipe> {

    private static final long serialVersionUID = 1954969957L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipe recipe = new QRecipe("recipe");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    public final NumberPath<Integer> cookingTime = createNumber("cookingTime", Integer.class);

    public final StringPath cuisineType = createString("cuisineType");

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath description = createString("description");

    public final StringPath dishCategory = createString("dishCategory");

    public final ListPath<com.recipe.universe.domain.recipe.ingredient.entity.DishIngredient, com.recipe.universe.domain.recipe.ingredient.entity.QDishIngredient> dishIngredients = this.<com.recipe.universe.domain.recipe.ingredient.entity.DishIngredient, com.recipe.universe.domain.recipe.ingredient.entity.QDishIngredient>createList("dishIngredients", com.recipe.universe.domain.recipe.ingredient.entity.DishIngredient.class, com.recipe.universe.domain.recipe.ingredient.entity.QDishIngredient.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike> likes = this.<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike>createList("likes", com.recipe.universe.domain.like.entity.UserLike.class, com.recipe.universe.domain.like.entity.QUserLike.class, PathInits.DIRECT2);

    public final StringPath mealType = createString("mealType");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> preparationTime = createNumber("preparationTime", Integer.class);

    public final ListPath<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings> ratings = this.<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings>createList("ratings", com.recipe.universe.domain.rating.entity.UserDishRatings.class, com.recipe.universe.domain.rating.entity.QUserDishRatings.class, PathInits.DIRECT2);

    public final NumberPath<Integer> recipeLevel = createNumber("recipeLevel", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> servingSize = createNumber("servingSize", Integer.class);

    public final ListPath<com.recipe.universe.domain.recipe.step.entity.RecipeStep, com.recipe.universe.domain.recipe.step.entity.QRecipeStep> steps = this.<com.recipe.universe.domain.recipe.step.entity.RecipeStep, com.recipe.universe.domain.recipe.step.entity.QRecipeStep>createList("steps", com.recipe.universe.domain.recipe.step.entity.RecipeStep.class, com.recipe.universe.domain.recipe.step.entity.QRecipeStep.class, PathInits.DIRECT2);

    public final com.recipe.universe.domain.user.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QRecipe(String variable) {
        this(Recipe.class, forVariable(variable), INITS);
    }

    public QRecipe(Path<? extends Recipe> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipe(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipe(PathMetadata metadata, PathInits inits) {
        this(Recipe.class, metadata, inits);
    }

    public QRecipe(Class<? extends Recipe> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.recipe.universe.domain.user.user.entity.QUser(forProperty("user")) : null;
    }

}

