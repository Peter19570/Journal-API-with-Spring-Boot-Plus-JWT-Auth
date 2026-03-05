package com.example.journal.apps.users.dto;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String email,
        String firstName,
        String lastName,
        String fullName,
        Integer age,
        String city,
        String country,
        String bio,
        String phone,
        Boolean isActive,
        Instant createdAt
) {
}
