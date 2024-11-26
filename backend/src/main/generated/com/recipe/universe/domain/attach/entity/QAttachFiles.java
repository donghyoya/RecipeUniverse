package com.recipe.universe.domain.attach.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachFiles is a Querydsl query type for AttachFiles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttachFiles extends EntityPathBase<AttachFiles> {

    private static final long serialVersionUID = 1085633052L;

    public static final QAttachFiles attachFiles = new QAttachFiles("attachFiles");

    public final com.recipe.universe.domain.QBaseEntity _super = new com.recipe.universe.domain.QBaseEntity(this);

    //inherited
    public final BooleanPath delFlag = _super.delFlag;

    public final NumberPath<Long> entityId = createNumber("entityId", Long.class);

    public final EnumPath<EntityType> entityType = createEnum("entityType", EntityType.class);

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileType = createString("fileType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath storePath = createString("storePath");

    public QAttachFiles(String variable) {
        super(AttachFiles.class, forVariable(variable));
    }

    public QAttachFiles(Path<? extends AttachFiles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachFiles(PathMetadata metadata) {
        super(AttachFiles.class, metadata);
    }

}

