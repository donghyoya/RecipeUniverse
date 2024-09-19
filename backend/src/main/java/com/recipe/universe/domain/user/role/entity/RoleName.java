package com.recipe.universe.domain.user.role.entity;

public enum RoleName {
    ROLE_ADMIN("ROLE_ADMIN", "ROLE_ADMIN > ROLE_MANAGER"),
    ROLE_MANAGER("ROLE_MANAGER", "ROLE_MANAGER > ROLE_USER"),
    ROLE_USER("ROLE_USER", ""),
    ROLE_BANNED("ROLE_BANNED", "ROLE_USER > ROLE_BANNED");

    private final String roleName;
    private final String expression;

    RoleName(String roleName, String expression) {
        this.roleName = roleName;
        this.expression = expression;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getExpression() {
        return expression;
    }
}
