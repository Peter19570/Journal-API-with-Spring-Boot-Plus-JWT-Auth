package com.example.journal.apps.journals.service;

import com.example.journal.apps.common.Authenticated;
import com.example.journal.apps.journals.dto.JournalRequestDto;
import com.example.journal.apps.journals.dto.JournalResponseDto;
import com.example.journal.apps.journals.mapper.JournalMapper;
import com.example.journal.apps.journals.model.Journal;
import com.example.journal.apps.journals.repo.JournalRepo;
import com.example.journal.exception.custom.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepo journalRepo;
    private final JournalMapper journalMapper;
    private final Authenticated authenticated;


    @Transactional
    public JournalResponseDto createJournal(JournalRequestDto requestDto){
        Journal journal = journalMapper.toEntity(requestDto);
        journal.setUser(authenticated.currentUser());
        Journal savedJournal = journalRepo.saveAndFlush(journal);
        return journalMapper.toDto(savedJournal);
    }

    public Page<JournalResponseDto> getAllJournal(Pageable pageable){
        Page<Journal> journalPage = journalRepo.
                findAllByUser(authenticated.currentUser(), pageable);
        return journalPage.map(journalMapper::toDto);
    }

    public JournalResponseDto getJournal(UUID id){
        Journal journal = journalRepo.findByIdAndUser(id, authenticated.currentUser())
                .orElseThrow(()->new NotFoundException("Journal not found"));
        return journalMapper.toDto(journal);
    }

    @Transactional
    public JournalResponseDto updateJournal(UUID id, JournalRequestDto requestDto){
        Journal journal = journalRepo.findByIdAndUser(id, authenticated.currentUser())
                .orElseThrow(()->new NotFoundException("Journal not found"));
        journalMapper.toEntityFromDto(requestDto, journal);
        return journalMapper.toDto(journal);
    }

    public void deleteJournal(UUID id){
        Journal journal = journalRepo.findByIdAndUser(id, authenticated.currentUser())
                .orElseThrow(()->new NotFoundException("Journal not found"));
        journalRepo.delete(journal);
    }


}
