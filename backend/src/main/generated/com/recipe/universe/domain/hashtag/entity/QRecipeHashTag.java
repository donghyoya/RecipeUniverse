package com.recipe.universe.domain.hashtag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecipeHashTag is a Querydsl query type for RecipeHashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeHashTag extends EntityPathBase<RecipeHashTag> {

    private static final long serialVersionUID = 1619661417L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecipeHashTag recipeHashTag = new QRecipeHashTag("recipeHashTag");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final QHashTag hashTag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.recipe.universe.domain.recipe.recipe.entity.QRecipe recipe;

    public final NumberPath<Long> recipeId = createNumber("recipeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath tagname = createString("tagname");

    public QRecipeHashTag(String variable) {
        this(RecipeHashTag.class, forVariable(variable), INITS);
    }

    public QRecipeHashTag(Path<? extends RecipeHashTag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecipeHashTag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecipeHashTag(PathMetadata metadata, PathInits inits) {
        this(RecipeHashTag.class, metadata, inits);
    }

    public QRecipeHashTag(Class<? extends RecipeHashTag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hashTag = inits.isInitialized("hashTag") ? new QHashTag(forProperty("hashTag")) : null;
        this.recipe = inits.isInitialized("recipe") ? new com.recipe.universe.domain.recipe.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
    }

}

