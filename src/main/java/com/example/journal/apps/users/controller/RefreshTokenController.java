package com.example.journal.apps.users.controller;

import com.example.journal.apps.common.ApiResponseDto;
import com.example.journal.apps.users.dto.AuthResponseDto;
import com.example.journal.apps.users.dto.RefreshTokenRequestDto;
import com.example.journal.apps.users.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> refreshToken(
            @RequestBody @Valid RefreshTokenRequestDto requestDto){
        AuthResponseDto responseDto = authService.refreshToken(requestDto);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ApiResponseDto<>("New tokens", responseDto));
    }
}
