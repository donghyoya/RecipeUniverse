package com.recipe.universe.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserReview is a Querydsl query type for UserReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserReview extends EntityPathBase<UserReview> {

    private static final long serialVersionUID = -265282260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserReview userReview = new QUserReview("userReview");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike> likes = this.<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike>createList("likes", com.recipe.universe.domain.like.entity.UserLike.class, com.recipe.universe.domain.like.entity.QUserLike.class, PathInits.DIRECT2);

    public final NumberPath<Double> rating = createNumber("rating", Double.class);

    public final com.recipe.universe.domain.recipe.recipe.entity.QRecipe recipe;

    public final NumberPath<Long> recipeId = createNumber("recipeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath review = createString("review");

    public final com.recipe.universe.domain.user.user.entity.QUser user;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserReview(String variable) {
        this(UserReview.class, forVariable(variable), INITS);
    }

    public QUserReview(Path<? extends UserReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserReview(PathMetadata metadata, PathInits inits) {
        this(UserReview.class, metadata, inits);
    }

    public QUserReview(Class<? extends UserReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.recipe = inits.isInitialized("recipe") ? new com.recipe.universe.domain.recipe.recipe.entity.QRecipe(forProperty("recipe"), inits.get("recipe")) : null;
        this.user = inits.isInitialized("user") ? new com.recipe.universe.domain.user.user.entity.QUser(forProperty("user")) : null;
    }

}

