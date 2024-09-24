package com.recipe.universe.domain.rating.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserDishRatings is a Querydsl query type for UserDishRatings
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDishRatings extends EntityPathBase<UserDishRatings> {

    private static final long serialVersionUID = -1593093565L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDishRatings userDishRatings = new QUserDishRatings("userDishRatings");

    public final com.recipe.universe.domain.dish.dish.entity.QDish dish;

    public final NumberPath<Long> dishId = createNumber("dishId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final StringPath review = createString("review");

    public final com.recipe.universe.domain.user.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserDishRatings(String variable) {
        this(UserDishRatings.class, forVariable(variable), INITS);
    }

    public QUserDishRatings(Path<? extends UserDishRatings> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserDishRatings(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserDishRatings(PathMetadata metadata, PathInits inits) {
        this(UserDishRatings.class, metadata, inits);
    }

    public QUserDishRatings(Class<? extends UserDishRatings> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dish = inits.isInitialized("dish") ? new com.recipe.universe.domain.dish.dish.entity.QDish(forProperty("dish"), inits.get("dish")) : null;
        this.user = inits.isInitialized("user") ? new com.recipe.universe.domain.user.user.entity.QUser(forProperty("user")) : null;
    }

}

