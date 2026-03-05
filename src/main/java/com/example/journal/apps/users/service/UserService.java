package com.example.journal.apps.users.service;

import com.example.journal.apps.common.Authenticated;
import com.example.journal.apps.profiles.mapper.ProfileMapper;
import com.example.journal.apps.profiles.model.Profile;
import com.example.journal.apps.profiles.repo.ProfileRepo;
import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.dto.UserResponseDto;
import com.example.journal.apps.users.dto.UserUpdateRequestDto;
import com.example.journal.apps.users.mapper.UserMapper;
import com.example.journal.apps.users.model.User;
import com.example.journal.apps.users.repo.UserRepo;
import com.example.journal.exception.custom.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;
    private final ProfileRepo profileRepo;
    private final Authenticated authenticated;

    public UserResponseDto getUserAndProfile(){
        return userMapper.toDto(authenticated.currentUser());
    }

    @Transactional
    public UserResponseDto updateUserAndProfile(UserUpdateRequestDto requestDto){
        Profile profile = profileRepo.findByUser(authenticated.currentUser())
                .orElseThrow(()->new NotFoundException("Profile not found"));
        userMapper.toEntityFromDto(requestDto, authenticated.currentUser());
        profileMapper.toEntityFromDto(requestDto, profile);
        return userMapper.toDto(authenticated.currentUser());
    }

    @Transactional
    public void deleteUserAndProfile(){
        User user = authenticated.currentUser();
        user.setIsActive(false);
        userRepo.save(user);
    }
}
