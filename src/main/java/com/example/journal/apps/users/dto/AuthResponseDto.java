package com.example.journal.apps.users.dto;

public record AuthResponseDto(
        String accessToken,
        String refreshToken
) {
}
