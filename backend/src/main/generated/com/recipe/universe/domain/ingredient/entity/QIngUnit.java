package com.recipe.universe.domain.ingredient.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIngUnit is a Querydsl query type for IngUnit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIngUnit extends EntityPathBase<IngUnit> {

    private static final long serialVersionUID = -1833629340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIngUnit ingUnit = new QIngUnit("ingUnit");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIngredient ingredient;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sAmt = createNumber("sAmt", Long.class);

    public final EnumPath<SUnit> sUnit = createEnum("sUnit", SUnit.class);

    public final com.recipe.universe.domain.unit.entity.QUnit unit;

    public QIngUnit(String variable) {
        this(IngUnit.class, forVariable(variable), INITS);
    }

    public QIngUnit(Path<? extends IngUnit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIngUnit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIngUnit(PathMetadata metadata, PathInits inits) {
        this(IngUnit.class, metadata, inits);
    }

    public QIngUnit(Class<? extends IngUnit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ingredient = inits.isInitialized("ingredient") ? new QIngredient(forProperty("ingredient")) : null;
        this.unit = inits.isInitialized("unit") ? new com.recipe.universe.domain.unit.entity.QUnit(forProperty("unit")) : null;
    }

}

