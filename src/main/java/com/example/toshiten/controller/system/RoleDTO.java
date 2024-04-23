package com.example.toshiten.controller.system;

import com.example.toshiten.service.accesscontrol.Permission;
import com.example.toshiten.service.accesscontrol.Role;

import java.util.List;

public record RoleDTO(
        long id,
        String name,
        List<PermissionSetting> permissions
) {
    public record PermissionSetting(
            String name,
            boolean enabled
    ) {
    }

    public static RoleDTO from(Role role, List<Permission> allPermissions) {
        return new RoleDTO(
                role.getId(),
                role.getName(),
                toPermissionSettings(allPermissions, role.getPermissions())
        );
    }

    private static List<PermissionSetting> toPermissionSettings(List<Permission> allPermissions, List<Permission> havingPermissions) {
        var havingPermissionNames = havingPermissions.stream()
                .map(Permission::name)
                .toList();

        return allPermissions.stream()
                .map(permission -> new PermissionSetting(permission.name(), havingPermissionNames.contains(permission.name())))
                .toList();
    }
}
