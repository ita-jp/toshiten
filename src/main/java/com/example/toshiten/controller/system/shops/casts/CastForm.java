package com.example.toshiten.controller.system.shops.casts;

import com.example.toshiten.service.cast.CastEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CastForm {
    private String name;
    private Integer age;
    private Integer height;
    private Integer sizeBust;
    private Integer sizeWaist;
    private Integer sizeHip;
    private String zodiacSign;
    private String bloodType;
    private String castComment;
    private String shopComment;

    public static CastForm from(CastEntity cast) {
        return new CastForm(
                cast.name(),
                cast.age(),
                cast.height(),
                cast.sizeBust(),
                cast.sizeWaist(),
                cast.sizeHip(),
                cast.zodiacSign(),
                cast.bloodType(),
                cast.castComment(),
                cast.shopComment()
        );
    }

    public CastEntity toEntity(long shopId) {
        return toEntity(shopId, null);
    }

    public CastEntity toEntity(long shopId, Long castId) {
        return new CastEntity(
                castId,
                name,
                age,
                height,
                sizeBust,
                sizeWaist,
                sizeHip,
                zodiacSign,
                bloodType,
                castComment,
                shopComment,
                shopId,
                true
        );
    }
}
