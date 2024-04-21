package com.example.toshiten.repository.user;

public record UserRecord(
        long id,
        String username,
        String password,
        boolean enabled
) {
}
