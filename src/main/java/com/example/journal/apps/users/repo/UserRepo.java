package com.example.journal.apps.users.repo;

import com.example.journal.apps.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    Boolean existsByEmailAndIsActive(String email, Boolean isActive);

    Optional<User> findByEmailAndIsActive(String email, Boolean isActive);

    Optional<User> findByIdAndIsActive(UUID id, Boolean isActive);

    String id(UUID id);
}
