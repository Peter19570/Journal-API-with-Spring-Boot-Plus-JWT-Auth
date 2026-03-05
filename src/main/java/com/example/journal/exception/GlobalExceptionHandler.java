package com.example.journal.exception;

import com.example.journal.apps.common.ApiResponseDto;
import com.example.journal.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseDto<String>> handleNotFoundError(NotFoundException error){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto<>("Not found", error.getMessage()));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponseDto<String>> handleAlreadyExistError(AlreadyExistException error){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponseDto<>("Already exists", error.getMessage()));
    }

    @ExceptionHandler(PasswordMatchException.class)
    public ResponseEntity<ApiResponseDto<String>> handlePasswordError(PasswordMatchException error){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ApiResponseDto<>("Password mismatch", error.getMessage()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ApiResponseDto<String>> handleTokenError(TokenExpiredException error){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ApiResponseDto<>("Bad Token", error.getMessage()));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ApiResponseDto<String>> handleUuidError(UnAuthorizedException error){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ApiResponseDto<>("UUID error", error.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<String>> handleNullError(MethodArgumentNotValidException error){
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ApiResponseDto<>("Null error", error.getMessage()));
    }
}
