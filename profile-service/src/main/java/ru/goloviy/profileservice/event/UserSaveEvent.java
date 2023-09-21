package ru.goloviy.profileservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.goloviy.profileservice.model.User;

@Getter
public class UserSaveEvent extends ApplicationEvent {
    private final User user;
    public UserSaveEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
