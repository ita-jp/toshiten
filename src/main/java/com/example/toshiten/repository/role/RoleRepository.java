package com.example.toshiten.repository.role;

import com.example.toshiten.service.accesscontrol.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRepository {

    @Select("""
            SELECT
                r.id
              , r.name
            FROM roles r
            ORDER BY r.id
            """)
    List<Role> select();
}
