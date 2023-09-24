package ru.goloviy.filestorageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.filestorageservice.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);
}
