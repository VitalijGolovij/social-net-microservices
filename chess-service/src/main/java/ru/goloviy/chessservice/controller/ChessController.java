package ru.goloviy.chessservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.goloviy.chessservice.dto.CreateGameRequest;
import ru.goloviy.chessservice.service.ChessService;

@RestController
@RequestMapping("/chess")
public class ChessController {
    private final ChessService chessService;

    @Autowired
    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody CreateGameRequest request){
        chessService.createGame(request.getUser1(), request.getUser2());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
