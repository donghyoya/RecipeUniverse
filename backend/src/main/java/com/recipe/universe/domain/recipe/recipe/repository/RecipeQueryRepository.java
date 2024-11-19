package com.recipe.universe.domain.recipe.recipe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.universe.domain.hashtag.entity.QRecipeHashTag;
import com.recipe.universe.domain.like.entity.QUserLike;
import com.recipe.universe.domain.recipe.controller.form.RecipeSortOption;
import com.recipe.universe.domain.recipe.recipe.dto.RecipeSearchDto;
import com.recipe.universe.domain.recipe.recipe.entity.QRecipe;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
import com.recipe.universe.domain.recipe.recipe.entity.view.QRecipeSortView;
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
public class RecipeQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<Recipe> findAllRecipe(Pageable pageable){
        QRecipe qRecipe = QRecipe.recipe;
        List<Recipe> content = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Recipe> countQuery = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );
    }

    public Page<Recipe> findAllRecipeWithHashTag(Pageable pageable){
        QRecipe qRecipe = QRecipe.recipe;
        List<Recipe> content = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .leftJoin(qRecipe.recipeHashTags, QRecipeHashTag.recipeHashTag)
                .leftJoin(qRecipe.likes, QUserLike.userLike)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Recipe> countQuery = queryFactory
                .select(qRecipe)
                .from(qRecipe);

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );
    }

    public Page<RecipeSearchDto> searchRecipe(
            String recipeName,
            RecipeDifficulty difficulty,
            Integer cookingTime,
            Integer servingSize,
            RecipeSortOption recipeSortOption,
            Pageable pageable
    ){
        Predicate condition = combinedRecipeQueryCondition(recipeName,difficulty, cookingTime,servingSize);
        OrderSpecifier sort = recipeOrderBy(recipeSortOption);
        QRecipe qRecipe = QRecipe.recipe;
        QRecipeSortView qRecipeSortView = QRecipeSortView.recipeSortView;

        List<RecipeSearchDto> content = queryFactory
                .select(
                        Projections.constructor(
                                RecipeSearchDto.class,
                                qRecipe.id,
                                qRecipe.name,
                                qRecipe.difficulty,
                                qRecipe.servingSize,
                                qRecipe.cookingTime,
                                qRecipeSortView.avgRating,
                                qRecipeSortView.reviewSize,
                                qRecipeSortView.likeCount
                        )
                )
                .from(qRecipe)
                .join(qRecipeSortView).on(qRecipe.id.eq(qRecipeSortView.id))
                .where(
                        condition
                )
                .orderBy(sort)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Recipe> countQuery = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .where(
                        condition
                );

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );
    }

    public Page<RecipeSearchDto> findByUserId(Long userId, Pageable pageable){
        QRecipe qRecipe = QRecipe.recipe;
        QRecipeSortView qRecipeSortView = QRecipeSortView.recipeSortView;

        List<RecipeSearchDto> content = queryFactory
                .select(
                        Projections.constructor(
                                RecipeSearchDto.class,
                                qRecipe.id,
                                qRecipe.name,
                                qRecipe.difficulty,
                                qRecipe.servingSize,
                                qRecipe.cookingTime,
                                qRecipeSortView.avgRating,
                                qRecipeSortView.reviewSize,
                                qRecipeSortView.likeCount
                        )
                )
                .from(qRecipe)
                .join(qRecipeSortView).on(qRecipe.id.eq(qRecipeSortView.id))
                .where(
                        userId(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Recipe> countQuery = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .where(
                        userId(userId)
                );
        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );

    }

    /* 조건 식 등 */

    private BooleanExpression recipeNameEq(String name){
        return name == null ? null :
                QRecipe.recipe.name.eq(name);
    }

    private BooleanExpression difficultyEq(RecipeDifficulty difficulty){
        return difficulty == null ? null :
                QRecipe.recipe.difficulty.eq(difficulty);

    }

    /**
     * 특정 조리시간 이하인가?
     * @param cookingTime
     * @return
     */
    private BooleanExpression cookingTimeLoe(Integer cookingTime){
        return cookingTime == null ? null :
                QRecipe.recipe.cookingTime.loe(cookingTime);
    }

    private BooleanExpression servingSize(Integer servingSize){
        if(servingSize == null){
            return null;
        }else if(servingSize <= 2){
            return QRecipe.recipe.servingSize.eq(servingSize);
        }else {
            return QRecipe.recipe.servingSize.goe(servingSize);
        }
    }

    private BooleanExpression userId(Long userId){
        return userId == null ? null : QRecipe.recipe.userId.eq(userId);
    }

    private Predicate combinedRecipeQueryCondition(
            String recipeName,
            RecipeDifficulty difficulty,
            Integer cookingTime,
            Integer servingSize
    ){
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(recipeNameEq(recipeName))
                .and(difficultyEq(difficulty))
                .and(cookingTimeLoe(cookingTime))
                .and(servingSize(servingSize));
        return builder;
    }

    private OrderSpecifier recipeOrderBy(RecipeSortOption recipeSortOption){
        if(recipeSortOption == RecipeSortOption.AvgRating){
            return QRecipeSortView.recipeSortView.avgRating.desc();
        }else if(recipeSortOption == RecipeSortOption.LikeSize){
            return QRecipeSortView.recipeSortView.likeCount.desc();
        }else if(recipeSortOption == RecipeSortOption.ReviewSize){
            return QRecipeSortView.recipeSortView.reviewSize.desc();
        }else {
            return QRecipe.recipe.regDate.desc();
        }
    }

}
