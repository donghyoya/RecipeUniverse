package com.recipe.universe.domain.recipe.recipe.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "recipe_sort_view")
public class RecipeSortView {
    @Id
    @Column(name = "recipe_id")
    private Long id;

    @Column(name = "avg_rating")
    private Double avgRating;

    @Column(name = "review_size")
    private Integer reviewSize;

    @Column(name = "like_size")
    private Integer likeCount;
}
