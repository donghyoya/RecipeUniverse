package com.recipe.universe.domain.recipe.recipe.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecipeSortView is a Querydsl query type for RecipeSortView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecipeSortView extends EntityPathBase<RecipeSortView> {

    private static final long serialVersionUID = 2026284355L;

    public static final QRecipeSortView recipeSortView = new QRecipeSortView("recipeSortView");

    public final NumberPath<Double> avgRating = createNumber("avgRating", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Integer> reviewSize = createNumber("reviewSize", Integer.class);

    public QRecipeSortView(String variable) {
        super(RecipeSortView.class, forVariable(variable));
    }

    public QRecipeSortView(Path<? extends RecipeSortView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecipeSortView(PathMetadata metadata) {
        super(RecipeSortView.class, metadata);
    }

}

