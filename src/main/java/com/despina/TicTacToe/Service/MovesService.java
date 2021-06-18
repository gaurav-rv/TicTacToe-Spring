package com.despina.TicTacToe.Service;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.GameStatus;
import com.despina.TicTacToe.Entity.Move;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Exception.GameNotFound;
import com.despina.TicTacToe.Storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovesService {

    @Autowired
    GameStorage gameStorage;


    public Game startGame(Player player, int sizeOfBoard) {
       Game game=  Game.builder()
            .id(UUID.randomUUID().toString())
            .p1(1)
            .board(new int[sizeOfBoard][sizeOfBoard])
            .row(new int[sizeOfBoard])
            .col(new int[sizeOfBoard])
            .gameStatus(GameStatus.NEW).build();

        gameStorage.addGame(game);
        return game;
    }

    public void connectToGameByID() {

    }

    public void connectToRandomGame() {

    }

    /**
     * To check if the move is valid
     */
    public void verifyEachMove(Move move) {
    }

    public Game makeMove(Move move) throws GameNotFound {
        if (!gameStorage.getGame().containsKey(move.getGameId())) {
            throw new GameNotFound("Could not find game");
        }
        Game game = gameStorage.getGame().get(move.getGameId());

        int[][] board = game.getBoard();
        board[move.getX()][move.getY()] = move.getMarkers().getVal();

        if (checkWin(game, move)) {
            game.setGameStatus(GameStatus.FINISHED);
            game.setWinner(move.getMarkers());
        }
        return game;
    }

    private boolean checkWin(Game game, Move move) {
        int[] row = game.getRow();
        int[] col = game.getCol();
        int[][] board = game.getBoard();

        row[move.getX()] += move.getMarkers().getVal();
        col[move.getY()] += move.getMarkers().getVal();

        boolean leftCross = true;
        boolean rightCross = true;

        int val = move.getMarkers().getVal();

        int length = row.length;
        for (int i = 0; i < length; i++) {
            if (board[i][i] != val) {
                leftCross = false;
            }
            if (board[i][length - 1 - i] != val) {
                rightCross = false;
            }
        }
        return Math.abs(row[move.getX()]) == length || Math.abs(col[move.getY()]) == length || leftCross || rightCross;
    }
}
