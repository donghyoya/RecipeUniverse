package com.recipe.universe.domain.hashtag.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashTag is a Querydsl query type for HashTag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTag extends EntityPathBase<HashTag> {

    private static final long serialVersionUID = -1711093737L;

    public static final QHashTag hashTag = new QHashTag("hashTag");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final ListPath<RecipeHashTag, QRecipeHashTag> recipeHashTags = this.<RecipeHashTag, QRecipeHashTag>createList("recipeHashTags", RecipeHashTag.class, QRecipeHashTag.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath tagname = createString("tagname");

    public QHashTag(String variable) {
        super(HashTag.class, forVariable(variable));
    }

    public QHashTag(Path<? extends HashTag> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTag(PathMetadata metadata) {
        super(HashTag.class, metadata);
    }

}

