package com.recipe.universe.domain.recipe.recipe.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Immutable
@Subselect(
        """
            create view recipe_sort_view as
            select r.recipe_id as recipe_id,
                   coalesce(avg(ur.rating), 0) as avg_rating,
                   count(ur.review_id) as review_size,
                   count(ul.id) like_size
            from recipe r
            left join user_review ur on r.recipe_id = ur.recipe_id
            left join user_like ul on r.recipe_id = ul.recipe_id
            group by r.
                    recipe_id;
    
        """
)
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
