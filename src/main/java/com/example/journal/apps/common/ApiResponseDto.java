package com.example.journal.apps.common;

public record ApiResponseDto<T>(
        String msg,
        T data
) {
}
