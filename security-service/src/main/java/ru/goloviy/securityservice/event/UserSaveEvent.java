package ru.goloviy.securityservice.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.goloviy.securityservice.model.User;

@Getter
public class UserSaveEvent extends ApplicationEvent {
    private final User user;
    public UserSaveEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
