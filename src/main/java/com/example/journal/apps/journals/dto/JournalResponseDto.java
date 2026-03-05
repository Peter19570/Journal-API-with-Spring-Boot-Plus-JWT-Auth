package com.example.journal.apps.journals.dto;

import java.time.Instant;
import java.util.UUID;


public record JournalResponseDto(
        UUID id,
        String title,
        String content,
        Instant createdAt
) {
}
