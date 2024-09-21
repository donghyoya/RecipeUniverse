package com.recipe.universe.domain.dish.dish.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDish is a Querydsl query type for Dish
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDish extends EntityPathBase<Dish> {

    private static final long serialVersionUID = 1172095153L;

    public static final QDish dish = new QDish("dish");

    public final NumberPath<Integer> cokkingTime = createNumber("cokkingTime", Integer.class);

    public final StringPath cuisineType = createString("cuisineType");

    public final StringPath description = createString("description");

    public final StringPath dishCategory = createString("dishCategory");

    public final StringPath dishName = createString("dishName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> integeringredientsCnt = createNumber("integeringredientsCnt", Integer.class);

    public final StringPath mealType = createString("mealType");

    public final NumberPath<Integer> preparationTime = createNumber("preparationTime", Integer.class);

    public final NumberPath<Integer> recipeLevel = createNumber("recipeLevel", Integer.class);

    public final NumberPath<Integer> servingSize = createNumber("servingSize", Integer.class);

    public QDish(String variable) {
        super(Dish.class, forVariable(variable));
    }

    public QDish(Path<? extends Dish> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDish(PathMetadata metadata) {
        super(Dish.class, metadata);
    }

}

