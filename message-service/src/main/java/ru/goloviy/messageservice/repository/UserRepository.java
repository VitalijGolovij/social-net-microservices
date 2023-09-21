package ru.goloviy.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.messageservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}
