package ru.goloviy.chessservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import ru.goloviy.chessservice.exception.FillChessGameException;
import ru.goloviy.chessservice.exception.GameWithYourselfException;
import ru.goloviy.chessservice.exception.NullUserException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "chess")
@Getter
@ToString
public class ChessGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "move_count")
    private Integer moveCount;
    @Column(name = "move_history")
    private String moveHistory;
    @ManyToMany
    @JoinTable(
            name = "user_chess",
            joinColumns = @JoinColumn(name = "chess_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<User> players;

    public ChessGame(){
        moveCount = 0;
        moveHistory = "";
        this.players = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(id, chessGame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addPlayers(User player1, User player2){
        if (player1 == null || player2 == null)
            throw new NullUserException();
        if (player1.equals(player2))
            throw new GameWithYourselfException();
        if (players.size() == 2)
            throw new FillChessGameException();
        players.add(player1);
        players.add(player2);
    }
}
