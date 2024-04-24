package com.example.toshiten.repository.accesscontrol;

import com.example.toshiten.service.accesscontrol.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    @Select("""
            SELECT
                p.id
              , p.name
            FROM permissions p
            ORDER BY p.id 
            """)
    List<Permission> selectAll();

    @Delete("""
            DELETE
            FROM role_permissions rp
            WHERE rp.role_id = #{roleId}
            """)
    void deleteByRoleId(long roleId);

    @Insert("""
           <script>
             INSERT INTO role_permissions (role_id, permission_id) VALUES
             <foreach collection='permissionIds' item='pid' separator=','>
               (#{roleId}, #{pid})
             </foreach>
           </script>
           """)
    void insert(long roleId, List<Long> permissionIds);
}
