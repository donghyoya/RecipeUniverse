package com.recipe.universe.domain.recipe.recipe.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.universe.domain.hashtag.entity.QRecipeHashTag;
import com.recipe.universe.domain.like.entity.QUserLike;
import com.recipe.universe.domain.recipe.recipe.entity.QRecipe;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.recipe.recipe.entity.RecipeDifficulty;
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

    public Page<Recipe> searchRecipe(
            String recipeName,
            RecipeDifficulty difficulty,
            Integer cookingTime,
            Integer servingSize,
            Pageable pageable
    ){
        QRecipe qRecipe = QRecipe.recipe;
        List<Recipe> content = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .where(
                        recipeNameEq(recipeName),
                        difficultyEq(difficulty),
                        cookingTimeLoe(cookingTime),
                        servingSize(servingSize)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Recipe> countQuery = queryFactory
                .select(qRecipe)
                .from(qRecipe)
                .where(
                        recipeNameEq(recipeName),
                        difficultyEq(difficulty),
                        cookingTimeLoe(cookingTime),
                        servingSize(servingSize)
                );

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );
    }

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




}
