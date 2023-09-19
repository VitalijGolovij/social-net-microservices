package ru.goloviy.chessservice.service;

import ru.goloviy.chessservice.model.User;

public interface ChessService {
    public void createGame(User user1, User user2);
}
