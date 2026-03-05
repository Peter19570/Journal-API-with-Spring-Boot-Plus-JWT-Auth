package com.example.journal.apps.journals.repo;

import com.example.journal.apps.journals.model.Journal;
import com.example.journal.apps.users.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JournalRepo extends JpaRepository<Journal, UUID> {

    Page<Journal> findAllByUser(User user, Pageable pageable);

    Optional<Journal> findByTitleAndUser(String title, User user);

    Optional<Journal> findByIdAndUser(UUID id, User user);

    Boolean existsByTitleAndUser(String title, User user);
}
