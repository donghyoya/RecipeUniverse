package com.recipe.universe.domain.recipe.recipe.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.universe.domain.hashtag.entity.QRecipeHashTag;
import com.recipe.universe.domain.like.entity.QUserLike;
import com.recipe.universe.domain.recipe.recipe.entity.QRecipe;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
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
                .from(qRecipe)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                ()->countQuery.fetch().size()
        );
    }


}
