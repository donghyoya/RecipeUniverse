package com.recipe.universe.domain.user.role.entity;

import com.recipe.universe.domain.user.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class UserRole {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        user.addRole(this);
    }
}
