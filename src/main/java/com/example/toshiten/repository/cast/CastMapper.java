package com.example.toshiten.repository.cast;

import com.example.toshiten.service.cast.CastEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CastMapper {

    @Select("""
            SELECT
                c.id
              , c.name
              , c.age
              , c.height
              , c.size_bust
              , c.size_waist
              , c.size_hip
              , c.zodiac_sign
              , c.blood_type
              , c.cast_comment
              , c.shop_comment
              , c.shop_id
              , c.enabled
            FROM casts c
            WHERE c.shop_id = #{shopId}
            ORDER BY c.id
            """)
    List<CastEntity> selectByShopId(long shopId);

    @Select("""
            SELECT
                c.id
              , c.name
              , c.age
              , c.height
              , c.size_bust
              , c.size_waist
              , c.size_hip
              , c.zodiac_sign
              , c.blood_type
              , c.cast_comment
              , c.shop_comment
              , c.shop_id
              , c.enabled
            FROM casts c
            WHERE c.shop_id = #{shopId}
              AND c.id = #{castId}
            """)
    Optional<CastEntity> selectByCastId(long shopId, long castId);

    @Insert("""
            INSERT INTO casts (
                name
              , age
              , height
              , size_bust
              , size_waist
              , size_hip
              , zodiac_sign
              , blood_type
              , cast_comment
              , shop_comment
              , shop_id
              , enabled
            ) VALUES (
                #{name}
              , #{age}
              , #{height}
              , #{sizeBust}
              , #{sizeWaist}
              , #{sizeHip}
              , #{zodiacSign}
              , #{bloodType}
              , #{castComment}
              , #{shopComment}
              , #{shopId}
              , #{enabled}
            )
            """
    )
    void insert(CastEntity entity);
}