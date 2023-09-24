package ru.goloviy.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.securityservice.model.UserCredential;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Long> {
    Optional<UserCredential> findByUsername(String username);
}
