package com.example.journal.exception.custom;

public class PasswordMatchException extends RuntimeException {
    public PasswordMatchException(String msg) {
        super(msg);
    }
}
