package com.example.journal.apps.common;

import com.example.journal.apps.users.model.User;
import com.example.journal.apps.users.repo.UserRepo;
import com.example.journal.exception.custom.NotFoundException;
import com.example.journal.exception.custom.UnAuthorizedException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class Authenticated {

    private final UserRepo userRepo;

    public User currentUser(){
        String userIdStr = Objects.requireNonNull(SecurityContextHolder
                .getContext().getAuthentication()).getName();
        return userRepo.findByIdAndIsActive(UUID.fromString(userIdStr),true)
                .orElseThrow(()->new NotFoundException("User not found"));
    }
}
