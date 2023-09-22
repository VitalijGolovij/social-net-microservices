package ru.goloviy.emailsenderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.emailsenderservice.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
