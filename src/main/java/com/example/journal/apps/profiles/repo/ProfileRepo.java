package com.example.journal.apps.profiles.repo;

import com.example.journal.apps.profiles.model.Profile;
import com.example.journal.apps.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, UUID> {
    Optional<Profile> findByUser(User user);
}
