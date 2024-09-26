package com.recipe.universe.domain.ingredient.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDishIngredient is a Querydsl query type for DishIngredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDishIngredient extends EntityPathBase<DishIngredient> {

    private static final long serialVersionUID = 1316563053L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDishIngredient dishIngredient = new QDishIngredient("dishIngredient");

    public final NumberPath<Double> dAmount = createNumber("dAmount", Double.class);

    public final NumberPath<Long> diId = createNumber("diId", Long.class);

    public final com.recipe.universe.domain.dish.dish.entity.QDish dish;

    public final QIngredient ingredient;

    public QDishIngredient(String variable) {
        this(DishIngredient.class, forVariable(variable), INITS);
    }

    public QDishIngredient(Path<? extends DishIngredient> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDishIngredient(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDishIngredient(PathMetadata metadata, PathInits inits) {
        this(DishIngredient.class, metadata, inits);
    }

    public QDishIngredient(Class<? extends DishIngredient> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.recipe.universe.domain.dish.dish.entity.QDish(forProperty("dish"), inits.get("dish")) : null;
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
    }

}

