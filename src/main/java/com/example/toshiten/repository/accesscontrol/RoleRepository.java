package com.example.toshiten.repository.accesscontrol;

import com.example.toshiten.service.accesscontrol.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

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

    @Select("""
            SELECT
                r.id
              , r.name
            FROM roles r
            WHERE r.id = #{id}
            """)
    Optional<Role> selectById(long id);
}
