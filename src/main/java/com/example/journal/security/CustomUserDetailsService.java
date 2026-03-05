package com.example.journal.security;

import com.example.journal.apps.users.repo.UserRepo;
import com.example.journal.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepo userRepo;

    @Bean
    public UserDetailsService userDetailsService(){
      return username -> userRepo.findByEmailAndIsActive(username, true)
              .orElseThrow(()-> new NotFoundException("User email not found"));
    }
}
