package com.example.journal.apps.users.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequestDto(
        @NotNull String firstName,
        @NotNull String lastName,
        Integer age,
        String city,
        String country,
        String bio,
        @Size(min = 0, max = 15) String phone
) {
}
