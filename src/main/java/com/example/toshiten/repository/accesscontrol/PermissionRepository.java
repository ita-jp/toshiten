package com.example.toshiten.repository.accesscontrol;

import com.example.toshiten.service.accesscontrol.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionRepository {

    @Select("""
            SELECT
                p.id
              , p.name
            FROM permissions p
            JOIN role_permissions rp ON p.id = rp.permission_id
            WHERE rp.role_id = #{roleId}
            ORDER BY p.id
            """)
    List<Permission> selectByRoleId(long roleId);
}
