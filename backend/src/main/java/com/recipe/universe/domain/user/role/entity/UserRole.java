package com.recipe.universe.domain.user.role.entity;

import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(
    name = "user_roles",
    uniqueConstraints = @UniqueConstraint(
            columnNames = {"user_id", "role_id"} // 하나의 유저가 하나의 Role만을 가지도록조치
    )
)
@Entity
public class UserRole {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "user_id", updatable = false, insertable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        user.addRole(this);
    }
}
