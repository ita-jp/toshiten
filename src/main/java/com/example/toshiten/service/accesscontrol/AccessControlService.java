package com.example.toshiten.service.accesscontrol;

import com.example.toshiten.repository.accesscontrol.PermissionRepository;
import com.example.toshiten.repository.accesscontrol.RoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccessControlService {

    private static final String CACHE_ALL_PERMISSIONS = "name";

    private final RoleMapper roleRepository;
    private final PermissionRepository permissionRepository;

    public List<Role> findAllRoles() {
        return roleRepository.selectAllWithPermissions();
    }

    public Optional<Role> findRoleById(long id) {
        return roleRepository.selectById(id)
                .map(role -> {
                    var ps = permissionRepository.selectByRoleId(role.getId());
                    role.setPermissions(ps);
                    return role;
                });
    }

    @Cacheable(CACHE_ALL_PERMISSIONS)
    public List<Permission> findAllPermissions() {
        return permissionRepository.selectAll();
    }

    @Transactional
    public void updateRolePermissions(long roleId, List<Long> permissions) {
        permissionRepository.deleteByRoleId(roleId);
        permissionRepository.insert(roleId, permissions);
    }
}
