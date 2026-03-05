package com.example.journal.apps.users.controller;

import com.example.journal.apps.common.ApiResponseDto;
import com.example.journal.apps.users.dto.AuthRequestDto;
import com.example.journal.apps.users.dto.AuthResponseDto;
import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor()
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> register(
            @RequestBody @Valid UserRequestDto requestDto){
        AuthResponseDto responseDto = authService.registerUser(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDto<>("Register success", responseDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> login(
            @RequestBody @Valid AuthRequestDto requestDto){
        AuthResponseDto responseDto = authService.loginUser(requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>("Login success", responseDto));
    }
}
