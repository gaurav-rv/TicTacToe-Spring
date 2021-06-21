package com.despina.TicTacToe.Service;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.GameStatus;
import com.despina.TicTacToe.Entity.Markers;
import com.despina.TicTacToe.Entity.Move;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Exception.GameNotFound;
import com.despina.TicTacToe.Storage.GameStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MovesService {

    @Autowired
    private GameStorage gameStorage;

    @Autowired
    private GameService gameService;

    /**
     * To check if the move is valid check if block is already marked and check if the same player making move again before his turn.
     */
    public boolean verifyEachMove(Move move) throws Exception {
        Game game = gameService.getGamebyID(move.getGameId());

        int[][] board = game.getBoard();
        return Markers.contains(board[move.getX()][move.getY()]);
        // TODO
        // check if the same player making move again before his turn
    }

    public Game makeMove(Move move) throws Exception {

        Game game = gameService.getGamebyID(move.getGameId());

        if (verifyEachMove(move)) {
            throw new Exception("invalid move.");
        }

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
