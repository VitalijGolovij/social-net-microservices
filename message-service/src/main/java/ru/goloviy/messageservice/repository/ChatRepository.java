package ru.goloviy.messageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.messageservice.model.Chat;
import ru.goloviy.messageservice.model.User;

import java.util.Optional;
import java.util.Set;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> getChatById(Long id);
//    Chat getChatByMembers(Set<User> members);
    Optional<Chat> findByMembersIn(Set<User> members);
//    Boolean existsChatByMembers(Set<User> members);
}
