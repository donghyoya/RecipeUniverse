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

    public final ListPath<com.recipe.universe.domain.dish.dish.entity.Dish, com.recipe.universe.domain.dish.dish.entity.QDish> dishes = this.<com.recipe.universe.domain.dish.dish.entity.Dish, com.recipe.universe.domain.dish.dish.entity.QDish>createList("dishes", com.recipe.universe.domain.dish.dish.entity.Dish.class, com.recipe.universe.domain.dish.dish.entity.QDish.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath provider = createString("provider");

    public final StringPath pwd = createString("pwd");

    public final ListPath<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings> ratings = this.<com.recipe.universe.domain.rating.entity.UserDishRatings, com.recipe.universe.domain.rating.entity.QUserDishRatings>createList("ratings", com.recipe.universe.domain.rating.entity.UserDishRatings.class, com.recipe.universe.domain.rating.entity.QUserDishRatings.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

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

