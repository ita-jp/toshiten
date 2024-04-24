package com.example.toshiten.service.cast;

public record CastEntity(
        Long id,
        String name,
        int age,
        int height,
        int sizeBust,
        int sizeWaist,
        int sizeHip,
        String zodiacSign,
        String bloodType,
        String castComment,
        String shopComment,
        long shopId,
        boolean enabled
) {
}
