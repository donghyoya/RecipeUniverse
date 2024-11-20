package com.recipe.universe.domain.like.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.universe.domain.like.entity.QUserLike;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.entity.QRecipe;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.view.QRecipeSortView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class UserLikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<RecipeSearchDto> userLikeRecipe(Long userId, Pageable pageable){
        QUserLike userLike = QUserLike.userLike;
        QRecipe recipe = QRecipe.recipe;
        QRecipeSortView recipeSortView = QRecipeSortView.recipeSortView;
        List<RecipeSearchDto> content = queryFactory
                .select(
                        Projections.constructor(
                                RecipeSearchDto.class,
                                recipe.id,
                                recipe.name,
                                recipe.difficulty,
                                recipe.servingSize,
                                recipe.cookingTime,
                                recipeSortView.avgRating,
                                recipeSortView.reviewSize,
                                recipeSortView.likeCount
                        )
                )
                .from(userLike)
                .join(userLike.recipe, recipe)
                .join(recipeSortView).on(recipe.id.eq(recipeSortView.id))
                .where(
                        userId(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<UserLike> countQuery = queryFactory
                .select(userLike)
                .from(userLike)
                .where(
                        userId(userId)
                );

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );

    }

    public void userLikeReview(Long userId, Pageable pageable){

    }

    private BooleanExpression userId(Long userId){
        return userId == null ? null : QUserLike.userLike.userId.eq(userId);
    }
}
