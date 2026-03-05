package com.example.journal.apps.journals.mapper;

import com.example.journal.apps.journals.dto.JournalRequestDto;
import com.example.journal.apps.journals.dto.JournalResponseDto;
import com.example.journal.apps.journals.model.Journal;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JournalMapper {

    Journal toEntity(JournalRequestDto requestDto);

    JournalResponseDto toDto(Journal journal);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntityFromDto(JournalRequestDto requestDto, @MappingTarget Journal journal);
}
