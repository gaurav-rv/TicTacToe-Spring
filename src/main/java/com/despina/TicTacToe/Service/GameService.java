package com.despina.TicTacToe.Service;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.GameStatus;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Storage.GameStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameStorage gameStorage;

    public Game startGame(Player player, int sizeOfBoard) {
        Game game = Game.builder()
            .id(UUID.randomUUID().toString())
            .board(new int[sizeOfBoard][sizeOfBoard])
            .row(new int[sizeOfBoard])
            .col(new int[sizeOfBoard])
            .gameStatus(GameStatus.NEW).build();

        game.getConnectedPlayers().add(player);
        gameStorage.addGame(game);
        return game;
    }


    public Game connectToGameByID(Player player, String gameID) throws Exception {
        // update both players info
        Game game = getGamebyID(gameID);
        //TODO -verify if player doesnt exist
        game.getConnectedPlayers().add(player);
        return game;
    }

    public Game connectToRandomGame(Player player) {
        // update both players info
        Game game = getRandomGame();
        //TODO -veridy if player doesnt exist
        game.getConnectedPlayers().add(player);
        return game;
        // update both players info;
    }

    public Game getRandomGame() {
        return gameStorage.getRandomGame();
    }

    public Game getGamebyID(String gameID) throws Exception {
        return Optional.ofNullable(gameStorage.getGame(gameID)).get().orElseThrow(() -> new Exception("Game Not Found"));
    }

}
