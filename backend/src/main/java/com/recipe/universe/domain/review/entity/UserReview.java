package com.recipe.universe.domain.review.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@SQLRestriction("del_flag = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserReview extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @Column
    private Double rating;

    @Column
    private String review;

    /* 관계 : 유저관련 */

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void addUser(User user){
        this.user = user;
        user.addReview(this);
    }

    /* Recipe */

    @Column(name = "recipe_id", insertable = false, updatable = false)
    private Long recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;

    public void addRecipe(Recipe recipe){
        this.recipe = recipe;
        this.recipe.addRatings(this);
    }

    /* 좋아요 관련 */
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLike> likes;

    public void addLike(UserLike like){
        likes.add(like);
    }

    /* 로직 */
    public void update(Double rating, String review){
        this.rating = rating;
        this.review = review;
    }

    /* 생성 */

    public UserReview(Double rating, String review, User user, Recipe recipe) {
        this.rating = rating;
        this.review = review;
        addUser(user);
        addRecipe(recipe);
    }
}
