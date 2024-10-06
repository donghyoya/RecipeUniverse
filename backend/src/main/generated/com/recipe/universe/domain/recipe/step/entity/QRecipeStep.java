package com.recipe.universe.domain.recipe.step.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeStep is a Querydsl query type for RecipeStep
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeStep extends EntityPathBase<RecipeStep> {

    private static final long serialVersionUID = -400338289L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeStep recipeStep = new QRecipeStep("recipeStep");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath description = createString("description");

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> order = createNumber("order", Long.class);

    public final com.recipe.universe.domain.recipe.recipe.entity.QRecipe recipe;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QRecipeStep(String variable) {
        this(RecipeStep.class, forVariable(variable), INITS);
    }

    public QRecipeStep(Path<? extends RecipeStep> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeStep(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeStep(PathMetadata metadata, PathInits inits) {
        this(RecipeStep.class, metadata, inits);
    }

    public QRecipeStep(Class<? extends RecipeStep> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.recipe.universe.domain.recipe.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}

