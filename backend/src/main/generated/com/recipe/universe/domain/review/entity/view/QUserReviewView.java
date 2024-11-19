package com.recipe.universe.domain.review.entity.view;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserReviewView is a Querydsl query type for UserReviewView
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserReviewView extends EntityPathBase<UserReviewView> {

    private static final long serialVersionUID = 765292264L;

    public static final QUserReviewView userReviewView = new QUserReviewView("userReviewView");

    public final NumberPath<Integer> likeCount = createNumber("likeCount", Integer.class);

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public QUserReviewView(String variable) {
        super(UserReviewView.class, forVariable(variable));
    }

    public QUserReviewView(Path<? extends UserReviewView> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserReviewView(PathMetadata metadata) {
        super(UserReviewView.class, metadata);
    }

}

