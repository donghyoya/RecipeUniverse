package com.recipe.universe.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.review.dto.UserReviewWithLikeDto;
import com.recipe.universe.domain.review.entity.QUserReview;
import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.review.entity.view.QUserReviewView;
import com.recipe.universe.domain.user.user.entity.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class UserReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<UserReviewWithLikeDto> findReviewByUserId(
            Long userId,
            Pageable pageable
    ){
        QUserReview qUserReview = QUserReview.userReview;
        QUserReviewView view = QUserReviewView.userReviewView;
        List<UserReviewWithLikeDto> content = queryFactory
                .select(
                        Projections.constructor(
                                UserReviewWithLikeDto.class,
                                qUserReview.id,
                                qUserReview.rating,
                                qUserReview.review,
                                qUserReview.userId,
                                QUser.user.nickname,
                                qUserReview.recipeId,
                                view.likeCount
                        )
                )
                .from(qUserReview)
                .join(qUserReview.user, QUser.user)
                .join(view).on(qUserReview.id.eq(view.reviewId))
                .where(
                        userId(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<UserReview> countQuery = queryFactory
                .select(qUserReview)
                .from(qUserReview)
                .where(
                        userId(userId)
                );
        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );

    }

    public Page<UserReviewWithLikeDto> findReviewByRecipeId(
            Long recipeId,
            Pageable pageable
    ){
        QUserReview qUserReview = QUserReview.userReview;
        QUserReviewView view = QUserReviewView.userReviewView;
        List<UserReviewWithLikeDto> content = queryFactory
                .select(
                        Projections.constructor(
                                UserReviewWithLikeDto.class,
                                qUserReview.id,
                                qUserReview.rating,
                                qUserReview.review,
                                qUserReview.userId,
                                QUser.user.nickname,
                                qUserReview.recipeId,
                                view.likeCount
                        )
                )
                .from(qUserReview)
                .join(qUserReview.user, QUser.user)
                .join(view).on(qUserReview.id.eq(view.reviewId))
                .where(
                        recipeId(recipeId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<UserReview> countQuery = queryFactory
                .select(qUserReview)
                .from(qUserReview)
                .where(
                        recipeId(recipeId)
                );
        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );

    }


    /* 조건식 */
    private BooleanExpression userId(Long userId){
        return userId == null ? null : QUserReview.userReview.userId.eq(userId);
    }

    private BooleanExpression recipeId(Long recipeId){
        return recipeId == null ? null : QUserReview.userReview.recipeId.eq(recipeId);
    }

}
