package com.example.toshiten.service.accesscontrol;

import com.example.toshiten.repository.accesscontrol.PermissionRepository;
import com.example.toshiten.repository.accesscontrol.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessControlService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public List<Role> findAllRoles() {
        return roleRepository.select();
    }

    public Optional<Role> findRoleById(long id) {
        return roleRepository.selectById(id)
                .map(role -> {
                    var ps = permissionRepository.selectByRoleId(role.getId());
                    role.setPermissions(ps);
                    return role;
                });
    }
}
