package ru.goloviy.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goloviy.messageservice.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
    User getUserByUsername(String username);
}
