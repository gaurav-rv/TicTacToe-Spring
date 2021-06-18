package com.despina.TicTacToe.Service;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.GameStatus;
import com.despina.TicTacToe.Entity.Move;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Exception.GameNotFound;
import com.despina.TicTacToe.Storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MovesService {

    public Game startGame(Player player, int sizeOfBoard){
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setP1(1);
        game.setBoard(new int[sizeOfBoard][sizeOfBoard]);
        game.setRow(new int[sizeOfBoard]);
        game.setCol(new int[sizeOfBoard]);
        game.setGameStatus(GameStatus.NEW);

        GameStorage.getInstance().addGame(game);
        return game;
    }

    public Game makeMove(Move move) throws GameNotFound {

        if(!GameStorage.getInstance().getGame().containsKey(move.getGameId())) throw new GameNotFound("Could not find game");

        Game game = GameStorage.getInstance().getGame().get(move.getGameId());

        int[][] board = game.getBoard();
        board[move.getX()][move.getY()] = move.getMarkers().getVal();

        if(checkWin(game, move)){
            game.setGameStatus(GameStatus.FINISHED);
            game.setWinner(move.getMarkers());
        };
        return game;
    }

    private boolean checkWin(Game game, Move move) {
        int[] row = game.getRow();
        int[] col = game.getCol();
        int[][] board = game.getBoard();
        row[move.getX()]++;
        col[move.getY()]++;
        boolean leftCross = true;
        boolean rightCross = true;

        int val = move.getMarkers().getVal();

        for(int i = 0; i< row.length; i++){
            if(board[i][i] != val) leftCross = false;
            if(board[i][row.length-1 -i] != val) rightCross= false;
        }
        if(row[move.getX()] == row.length || col[move.getY()] == col.length || leftCross || rightCross) return true;
        return false;
    }
}
