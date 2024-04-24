package com.example.toshiten.controller.system;

import com.example.toshiten.service.accesscontrol.Role;

public record RoleListElementDTO(
        long id,
        String name,
        int permissionCount
) {
    public static RoleListElementDTO from(Role role) {
        return new RoleListElementDTO(
                role.getId(),
                role.getName(),
                role.getPermissions().size()
        );
    }
}
