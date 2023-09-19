package ru.goloviy.chessservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goloviy.chessservice.model.ChessGame;
import ru.goloviy.chessservice.model.User;
import ru.goloviy.chessservice.repository.ChessRepository;
import ru.goloviy.chessservice.repository.UserRepository;
import ru.goloviy.chessservice.service.ChessService;

@Service
public class ChessServiceImpl implements ChessService {
    private final UserRepository userRepository;
    private final ChessRepository chessRepository;

    @Autowired
    public ChessServiceImpl(UserRepository userRepository, ChessRepository chessRepository) {
        this.userRepository = userRepository;
        this.chessRepository = chessRepository;
    }

    @Override
    @Transactional
    public void createGame(User user1, User user2) {
        ChessGame chessGame = new ChessGame();
        chessGame.addPlayers(user1, user2);
        chessRepository.save(chessGame);
    }
}
