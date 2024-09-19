package com.recipe.universe.domain.user.role.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Role {
    @Id @GeneratedValue
    private Long id;

    private String roleName;
}
