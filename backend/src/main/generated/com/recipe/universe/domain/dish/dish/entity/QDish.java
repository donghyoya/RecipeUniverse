package com.recipe.universe.domain.dish.dish.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDish is a Querydsl query type for Dish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDish extends EntityPathBase<Dish> {

    private static final long serialVersionUID = 1172095153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDish dish = new QDish("dish");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    public final NumberPath<Integer> cookingTime = createNumber("cookingTime", Integer.class);

    public final StringPath cuisineType = createString("cuisineType");

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath description = createString("description");

    public final StringPath dishCategory = createString("dishCategory");

    public final ListPath<com.recipe.universe.domain.ingredient.entity.DishIngredient, com.recipe.universe.domain.ingredient.entity.QDishIngredient> dishIngredients = this.<com.recipe.universe.domain.ingredient.entity.DishIngredient, com.recipe.universe.domain.ingredient.entity.QDishIngredient>createList("dishIngredients", com.recipe.universe.domain.ingredient.entity.DishIngredient.class, com.recipe.universe.domain.ingredient.entity.QDishIngredient.class, PathInits.DIRECT2);

    public final StringPath dishName = createString("dishName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> integeringredientsCnt = createNumber("integeringredientsCnt", Integer.class);

    public final StringPath mealType = createString("mealType");

    public final NumberPath<Integer> preparationTime = createNumber("preparationTime", Integer.class);

    public final ListPath<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings> ratings = this.<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings>createList("ratings", com.recipe.universe.domain.rating.entity.UserDishRatings.class, com.recipe.universe.domain.rating.entity.QUserDishRatings.class, PathInits.DIRECT2);

    public final NumberPath<Integer> recipeLevel = createNumber("recipeLevel", Integer.class);

    public final ListPath<com.recipe.universe.domain.dish.recipe.entity.Recipe, com.recipe.universe.domain.dish.recipe.entity.QRecipe> recipes = this.<com.recipe.universe.domain.dish.recipe.entity.Recipe, com.recipe.universe.domain.dish.recipe.entity.QRecipe>createList("recipes", com.recipe.universe.domain.dish.recipe.entity.Recipe.class, com.recipe.universe.domain.dish.recipe.entity.QRecipe.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> servingSize = createNumber("servingSize", Integer.class);

    public final com.recipe.universe.domain.user.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QDish(String variable) {
        this(Dish.class, forVariable(variable), INITS);
    }

    public QDish(Path<? extends Dish> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDish(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDish(PathMetadata metadata, PathInits inits) {
        this(Dish.class, metadata, inits);
    }

    public QDish(Class<? extends Dish> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.recipe.universe.domain.user.user.entity.QUser(forProperty("user")) : null;
    }

}

