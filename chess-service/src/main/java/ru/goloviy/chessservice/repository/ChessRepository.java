package ru.goloviy.chessservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.goloviy.chessservice.model.ChessGame;
import ru.goloviy.chessservice.model.User;

public interface ChessRepository extends JpaRepository<ChessGame, Long> {

}
