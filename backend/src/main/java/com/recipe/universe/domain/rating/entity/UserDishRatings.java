package com.recipe.universe.domain.rating.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Dish;
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
public class UserDishRatings extends BaseEntity {
    @Id @GeneratedValue
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

    public void setUser(User user){
        this.user = user;
        user.addRatings(this);
    }

    /* 관계 : 요리관련 */

    @Column(name = "dish_id", insertable = false, updatable = false)
    private Long dishId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    public void setDish(Dish dish){
        this.dish = dish;
    }

    /* 좋아요 관련 */
    @OneToMany(mappedBy = "rating", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public UserDishRatings(Double rating, String review, User user, Dish dish) {
        this.rating = rating;
        this.review = review;
        setUser(user);
        setDish(dish);
    }
}
