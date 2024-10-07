package com.recipe.universe.domain.user.user.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.recipe.recipe.entity.Recipe;
import com.recipe.universe.domain.like.entity.UserLike;
import com.recipe.universe.domain.review.entity.UserReview;
import com.recipe.universe.domain.user.history.entity.UserHistory;
import com.recipe.universe.domain.user.role.entity.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@SQLRestriction("del_flag = false")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private Long id;

    @Column(nullable = false)
    private String userId;
    private String pwd;
    private String email;
    private String provider;

    /* 관계 : 권한 관련 */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles;

    public void addRole(UserRole userRole){
        roles.add(userRole);
    }

    /* 관계 : 음식 관련 */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes;

    public void addRecipes(Recipe recipe){
        recipes.add(recipe);
    }

    /* 관계 : 음식 평가 관련 */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserReview> reviews = new ArrayList<>();

    public void addReview(UserReview review){
        reviews.add(review);
    }

    /* 유저 이력 관련 */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserHistory> histories = new ArrayList<>();

    public void addHistory(UserHistory history){
        histories.add(history);
    }

    /* 좋아요 관련 */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLike> likes = new ArrayList<>();

    public void addLike(UserLike like){
        likes.add(like);
    }


    /* 생성 관련 */

    protected User(String userId, String pwd, String email, String provider) {
        this.userId = userId;
        this.pwd = pwd;
        this.email = email;
        this.provider = provider;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String userId;
        private String pwd;
        private String email;
        private String provider;

        public User build(){
            return new User(userId, pwd, email, provider);
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder pwd(String pwd) {
            this.pwd = pwd;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }
    }
}
