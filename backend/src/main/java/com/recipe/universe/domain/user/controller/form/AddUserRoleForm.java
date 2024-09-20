package com.recipe.universe.domain.user.controller.form;

import com.recipe.universe.domain.user.role.entity.RoleName;
import lombok.Data;

@Data
public class AddUserRoleForm {
    private RoleName roleName;
}
