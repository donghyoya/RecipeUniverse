package com.recipe.universe.domain.images.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QImageFiles is a Querydsl query type for ImageFiles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageFiles extends EntityPathBase<ImageFiles> {

    private static final long serialVersionUID = 1494701861L;

    public static final QImageFiles imageFiles = new QImageFiles("imageFiles");

    public final StringPath extension = createString("extension");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originalFileName = createString("originalFileName");

    public QImageFiles(String variable) {
        super(ImageFiles.class, forVariable(variable));
    }

    public QImageFiles(Path<? extends ImageFiles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImageFiles(PathMetadata metadata) {
        super(ImageFiles.class, metadata);
    }

}

