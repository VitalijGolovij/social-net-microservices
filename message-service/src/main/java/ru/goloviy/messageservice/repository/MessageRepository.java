package ru.goloviy.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goloviy.messageservice.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
