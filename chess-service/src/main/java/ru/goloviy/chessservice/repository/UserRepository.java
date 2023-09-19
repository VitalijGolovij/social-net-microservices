package ru.goloviy.chessservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.chessservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
