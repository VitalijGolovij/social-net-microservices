package ru.goloviy.chessservice.dto;

import lombok.Getter;
import lombok.Setter;
import ru.goloviy.chessservice.model.User;

@Getter
@Setter
public class CreateGameRequest {
    private User user1;
    private User user2;
}
