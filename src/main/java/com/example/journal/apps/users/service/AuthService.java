package com.example.journal.apps.users.service;

import com.example.journal.apps.common.Authenticated;
import com.example.journal.apps.profiles.mapper.ProfileMapper;
import com.example.journal.apps.profiles.model.Profile;
import com.example.journal.apps.profiles.repo.ProfileRepo;
import com.example.journal.apps.users.dto.AuthRequestDto;
import com.example.journal.apps.users.dto.AuthResponseDto;
import com.example.journal.apps.users.dto.RefreshTokenRequestDto;
import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.mapper.UserMapper;
import com.example.journal.apps.users.model.User;
import com.example.journal.apps.users.repo.UserRepo;
import com.example.journal.exception.custom.AlreadyExistException;
import com.example.journal.exception.custom.NotFoundException;
import com.example.journal.exception.custom.PasswordMatchException;
import com.example.journal.exception.custom.TokenExpiredException;
import com.example.journal.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Transactional
    public AuthResponseDto registerUser(UserRequestDto requestDto){
        if (userRepo.existsByEmailAndIsActive(requestDto.email(), true)){
            throw new AlreadyExistException("Email already taken");
        }
        if (!requestDto.confirmPassword().equals(requestDto.password())){
            throw new PasswordMatchException("Passwords dont match");
        }
        User user = userMapper.toEntity(requestDto);
        Profile profile = profileMapper.toEntity(requestDto);

        user.setPassword(passwordEncoder.encode(requestDto.password()));
        user.setProfile(profile);
        profile.setUser(user);

        profileRepo.save(profile);
        User savedUser = userRepo.saveAndFlush(user);

        String accessToken = jwtService.generateAccessToken(savedUser);
        String refreshToken = jwtService.generateRefreshToken(savedUser);
        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto loginUser(AuthRequestDto requestDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        requestDto.email(), requestDto.password()));
        User user = (User) authentication.getPrincipal();

        assert user != null;
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponseDto(accessToken, refreshToken);
    }

    public AuthResponseDto refreshToken(RefreshTokenRequestDto requestDto){
        String username = jwtService.extractUsername(requestDto.refreshToken());

        User user = userRepo.findByIdAndIsActive(UUID.fromString(username), true)
                .orElseThrow(()->new NotFoundException("User not found"));

        if (!jwtService.isTokenValid(requestDto.refreshToken(), user)){
            throw new TokenExpiredException("Refresh token is expired");
        }
        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponseDto(newAccessToken, newRefreshToken);
    }
}
