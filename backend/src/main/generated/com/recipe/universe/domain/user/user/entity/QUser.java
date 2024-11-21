package com.recipe.universe.domain.user.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 184542594L;

    public static final QUser user = new QUser("user");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final StringPath email = createString("email");

    public final ListPath<com.recipe.universe.domain.user.history.entity.UserHistory, com.recipe.universe.domain.user.history.entity.QUserHistory> histories = this.<com.recipe.universe.domain.user.history.entity.UserHistory, com.recipe.universe.domain.user.history.entity.QUserHistory>createList("histories", com.recipe.universe.domain.user.history.entity.UserHistory.class, com.recipe.universe.domain.user.history.entity.QUserHistory.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike> likes = this.<com.recipe.universe.domain.like.entity.UserLike, com.recipe.universe.domain.like.entity.QUserLike>createList("likes", com.recipe.universe.domain.like.entity.UserLike.class, com.recipe.universe.domain.like.entity.QUserLike.class, PathInits.DIRECT2);

    public final StringPath nickname = createString("nickname");

    public final StringPath provider = createString("provider");

    public final StringPath pwd = createString("pwd");

    public final ListPath<com.recipe.universe.domain.recipe.recipe.entity.Recipe, com.recipe.universe.domain.recipe.recipe.entity.QRecipe> recipes = this.<com.recipe.universe.domain.recipe.recipe.entity.Recipe, com.recipe.universe.domain.recipe.recipe.entity.QRecipe>createList("recipes", com.recipe.universe.domain.recipe.recipe.entity.Recipe.class, com.recipe.universe.domain.recipe.recipe.entity.QRecipe.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final ListPath<com.recipe.universe.domain.review.entity.UserReview, com.recipe.universe.domain.review.entity.QUserReview> reviews = this.<com.recipe.universe.domain.review.entity.UserReview, com.recipe.universe.domain.review.entity.QUserReview>createList("reviews", com.recipe.universe.domain.review.entity.UserReview.class, com.recipe.universe.domain.review.entity.QUserReview.class, PathInits.DIRECT2);

    public final ListPath<com.recipe.universe.domain.user.role.entity.UserRole, com.recipe.universe.domain.user.role.entity.QUserRole> roles = this.<com.recipe.universe.domain.user.role.entity.UserRole, com.recipe.universe.domain.user.role.entity.QUserRole>createList("roles", com.recipe.universe.domain.user.role.entity.UserRole.class, com.recipe.universe.domain.user.role.entity.QUserRole.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

