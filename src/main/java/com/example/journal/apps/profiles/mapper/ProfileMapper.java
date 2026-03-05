package com.example.journal.apps.profiles.mapper;

import com.example.journal.apps.profiles.model.Profile;
import com.example.journal.apps.users.dto.UserRequestDto;
import com.example.journal.apps.users.dto.UserUpdateRequestDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfileMapper {

    Profile toEntity(UserRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntityFromDto(UserUpdateRequestDto requestDto, @MappingTarget Profile profile);
}
