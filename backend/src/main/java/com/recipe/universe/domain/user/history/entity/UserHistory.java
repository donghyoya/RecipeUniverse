package com.recipe.universe.domain.user.history.entity;

import com.recipe.universe.domain.BaseEntity;
import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@SQLRestriction("del_flag = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserHistory extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private void addUser(User user){
        this.user = user;
        this.user.addHistory(this);
    }

    public UserHistory(User user) {
        this.user = user;
    }

}
