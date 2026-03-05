package com.example.journal.apps.users.controller;

import com.example.journal.apps.common.ApiResponseDto;
import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.dto.UserResponseDto;
import com.example.journal.apps.users.dto.UserUpdateRequestDto;
import com.example.journal.apps.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getUser(){
        return ResponseEntity.ok(new ApiResponseDto<>("Me", userService.getUserAndProfile()));
    }

    @PutMapping
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(
            @RequestBody @Valid UserUpdateRequestDto requestDto){
        UserResponseDto responseDto = userService.updateUserAndProfile(requestDto);
        return ResponseEntity.ok(new ApiResponseDto<>("User updated", responseDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(){
        userService.deleteUserAndProfile();
        return ResponseEntity.noContent().build();
    }
}
