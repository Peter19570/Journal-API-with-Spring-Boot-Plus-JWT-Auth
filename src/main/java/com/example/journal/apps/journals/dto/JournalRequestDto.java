package com.example.journal.apps.journals.dto;

import jakarta.validation.constraints.NotNull;

public record JournalRequestDto(
        @NotNull String title,
        @NotNull String content
) {
}
