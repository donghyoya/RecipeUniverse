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

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath provider = createString("provider");

    public final StringPath pwd = createString("pwd");

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

