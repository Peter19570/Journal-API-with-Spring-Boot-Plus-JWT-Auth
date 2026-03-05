package com.example.journal.apps.users.mapper;

import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.dto.UserResponseDto;
import com.example.journal.apps.users.dto.UserUpdateRequestDto;
import com.example.journal.apps.users.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(UserRequestDto requestDto);

    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    @Mapping(target = "age", source = "user.profile.age")
    @Mapping(target = "city", source = "user.profile.city")
    @Mapping(target = "country", source = "user.profile.country")
    @Mapping(target = "bio", source = "user.profile.bio")
    @Mapping(target = "phone", source = "user.profile.phone")
    UserResponseDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntityFromDto(UserUpdateRequestDto requestDto, @MappingTarget User user);
}
