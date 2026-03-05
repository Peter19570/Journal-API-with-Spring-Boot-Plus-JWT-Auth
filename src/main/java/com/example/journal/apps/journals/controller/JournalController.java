package com.example.journal.apps.journals.controller;

import com.example.journal.apps.common.ApiResponseDto;
import com.example.journal.apps.journals.dto.JournalRequestDto;
import com.example.journal.apps.journals.dto.JournalResponseDto;
import com.example.journal.apps.journals.service.JournalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<JournalResponseDto>> createJournal(
            @RequestBody @Valid JournalRequestDto requestDto){
        JournalResponseDto responseDto = journalService.createJournal(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponseDto<>("Journal Created", responseDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Page<JournalResponseDto>>> getAllJournal(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<JournalResponseDto> responseDto = journalService.getAllJournal(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>("All journals", responseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<JournalResponseDto>> getJournal(@PathVariable UUID id){
        JournalResponseDto responseDto = journalService.getJournal(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>("Journal found", responseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<JournalResponseDto>> updateJournal(
            @RequestBody @Valid JournalRequestDto requestDto, @PathVariable UUID id){
        JournalResponseDto responseDto = journalService.updateJournal(id, requestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponseDto<>("Journal updated", responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteJournal(@PathVariable UUID id){
        journalService.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }

}
