package com.recipe.universe.domain.unit.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnit is a Querydsl query type for Unit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnit extends EntityPathBase<Unit> {

    private static final long serialVersionUID = 2109586329L;

    public static final QUnit unit = new QUnit("unit");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.recipe.universe.domain.ingredient.entity.IngUnit, com.recipe.universe.domain.ingredient.entity.QIngUnit> ingUnits = this.<com.recipe.universe.domain.ingredient.entity.IngUnit, com.recipe.universe.domain.ingredient.entity.QIngUnit>createList("ingUnits", com.recipe.universe.domain.ingredient.entity.IngUnit.class, com.recipe.universe.domain.ingredient.entity.QIngUnit.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath unitName = createString("unitName");

    public QUnit(String variable) {
        super(Unit.class, forVariable(variable));
    }

    public QUnit(Path<? extends Unit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnit(PathMetadata metadata) {
        super(Unit.class, metadata);
    }

}

