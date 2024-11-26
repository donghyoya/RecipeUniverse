package com.recipe.universe.domain.ingredient.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngredient is a Querydsl query type for Ingredient
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngredient extends EntityPathBase<Ingredient> {

    private static final long serialVersionUID = 1742665779L;

    public static final QIngredient ingredient = new QIngredient("ingredient");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    public final StringPath category = createString("category");

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> ingId = createNumber("ingId", Long.class);

    public final StringPath ingName = createString("ingName");

    public final ListPath<IngUnit, QIngUnit> ingUnits = this.<IngUnit, QIngUnit>createList("ingUnits", IngUnit.class, QIngUnit.class, PathInits.DIRECT2);

    public final ListPath<com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient, com.recipe.universe.domain.recipe.ingredient.entity.QRecipeIngredient> recipeIngredients = this.<com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient, com.recipe.universe.domain.recipe.ingredient.entity.QRecipeIngredient>createList("recipeIngredients", com.recipe.universe.domain.recipe.ingredient.entity.RecipeIngredient.class, com.recipe.universe.domain.recipe.ingredient.entity.QRecipeIngredient.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QIngredient(String variable) {
        super(Ingredient.class, forVariable(variable));
    }

    public QIngredient(Path<? extends Ingredient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIngredient(PathMetadata metadata) {
        super(Ingredient.class, metadata);
    }

}

