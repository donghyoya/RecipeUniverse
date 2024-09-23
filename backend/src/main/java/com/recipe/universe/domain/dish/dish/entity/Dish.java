package com.recipe.universe.domain.dish.dish.entity;

import com.recipe.universe.domain.dish.recipe.entity.Recipe;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Dish {
    /**
     * ID
     */
    @Id @GeneratedValue
    private Long id;

    /**
     * 요리이름
     */
    @Column
    private String dishName;

    /**
     * 요리 설명
     */
    @Column
    private String description;

    /**
     * 음식 문화 종류
     */
    @Column
    private String cuisineType;

    /**
     * 요리종류
     */
    @Column
    private String mealType;

    /**
     * 준비시간
     */
    @Column
    private Integer preparationTime;

    /**
     * 요리시간
     */
    @Column
    private Integer cokkingTime;

    /**
     * 몇 인분
     */
    @Column
    private Integer servingSize;

    /**
     * 난이도
     */
    @Column
    private Integer recipeLevel;

    /**
     * 재료갯수
     */
    @Column
    private Integer integeringredientsCnt;

    /**
     * 요리 카테고리
     */
    @Column
    private String dishCategory;
}
