package com.example.toshiten.repository.shop;

import com.example.toshiten.service.shop.ShopEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopRepository {

    @Select("""
            SELECT
                s.id
              , s.name
            FROM shops s
            """)
    List<ShopEntity> select();
}
