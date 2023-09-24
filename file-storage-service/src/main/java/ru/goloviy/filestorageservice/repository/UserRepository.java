package ru.goloviy.filestorageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.filestorageservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
