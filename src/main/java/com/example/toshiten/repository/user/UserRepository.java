package com.example.toshiten.repository.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserRepository {

    @Select("""
            SELECT
                u.id
              , u.username
              , u.password
              , u.enabled
            FROM users u
            WHERE u.username = #{username}
            """)
    Optional<UserRecord> selectByUsername(@Param("username") String username);
}
