package com.example.toshiten.controller.system.shops.casts;

import com.example.toshiten.service.cast.CastEntity;

public record CastForm(
        String name,
        int age,
        int height,
        int sizeBust,
        int sizeWaist,
        int sizeHip,
        String zodiacSign,
        String bloodType,
        String castComment,
        String shopComment
) {
    public CastEntity toEntity(long shopId) {
        return new CastEntity(
                null,
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

    public CastForm() {
        this(null, 0, 0, 0, 0, 0, null, null, null, null);
    }
}
