package com.recipe.universe.domain.nutrition.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNutrition is a Querydsl query type for Nutrition
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNutrition extends EntityPathBase<Nutrition> {

    private static final long serialVersionUID = 522823159L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNutrition nutrition = new QNutrition("nutrition");

    public final NumberPath<Double> calcium = createNumber("calcium", Double.class);

    public final NumberPath<Double> calories = createNumber("calories", Double.class);

    public final NumberPath<Double> carbs = createNumber("carbs", Double.class);

    public final NumberPath<Double> fat = createNumber("fat", Double.class);

    public final com.recipe.universe.domain.ingredient.entity.QIngredient ingredient;

    public final NumberPath<Double> moisture = createNumber("moisture", Double.class);

    public final NumberPath<Double> nAmount = createNumber("nAmount", Double.class);

    public final NumberPath<Long> nid = createNumber("nid", Long.class);

    public final NumberPath<Double> potassium = createNumber("potassium", Double.class);

    public final NumberPath<Double> protein = createNumber("protein", Double.class);

    public final com.recipe.universe.domain.recipe.recipe.entity.QRecipe recipe;

    public final NumberPath<Double> sodium = createNumber("sodium", Double.class);

    public final NumberPath<Double> sugar = createNumber("sugar", Double.class);

    public QNutrition(String variable) {
        this(Nutrition.class, forVariable(variable), INITS);
    }

    public QNutrition(Path<? extends Nutrition> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNutrition(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNutrition(PathMetadata metadata, PathInits inits) {
        this(Nutrition.class, metadata, inits);
    }

    public QNutrition(Class<? extends Nutrition> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new com.recipe.universe.domain.ingredient.entity.QIngredient(forProperty("ingredient")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.recipe.universe.domain.recipe.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}

