package com.recipe.universe.domain.user.role.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Role {
    @Id @GeneratedValue
    private Long id;

    private String roleName;
    private String expression;

    public Role(String roleName, String expression) {
        this.roleName = roleName;
        this.expression = expression;
    }
}
