package com.recipe.universe.domain.review.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@Table(name = "user_review_view")
public class UserReviewView {
    @Id @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "like_size")
    private Integer likeCount;
}
