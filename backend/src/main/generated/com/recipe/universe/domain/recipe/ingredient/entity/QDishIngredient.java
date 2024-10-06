package com.recipe.universe.domain.recipe.ingredient.entity;

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

    private static final long serialVersionUID = 102466021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDishIngredient dishIngredient = new QDishIngredient("dishIngredient");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    public final NumberPath<Double> dAmount = createNumber("dAmount", Double.class);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath description = createString("description");

    public final NumberPath<Long> diId = createNumber("diId", Long.class);

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final NumberPath<Long> ingId = createNumber("ingId", Long.class);

    public final com.recipe.universe.domain.ingredient.entity.QIngredient ingredient;

    public final BooleanPath optional = createBoolean("optional");

    public final com.recipe.universe.domain.recipe.recipe.entity.QRecipe recipe;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath unit = createString("unit");

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
        this.ingredient = inits.isInitialized("ingredient") ? new com.recipe.universe.domain.ingredient.entity.QIngredient(forProperty("ingredient")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.recipe.universe.domain.recipe.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}

