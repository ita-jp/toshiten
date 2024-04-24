package com.example.toshiten.controller.system;

import java.util.List;

public record PermissionsForm(
        List<Long> permissions
) {
}
