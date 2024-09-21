package com.recipe.universe.domain.user.role.entity;

import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "user_roles")
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
