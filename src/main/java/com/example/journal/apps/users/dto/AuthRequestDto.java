package com.example.journal.apps.users.dto;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDto(
        @NotNull String email,
        @NotNull String password
) {
}
