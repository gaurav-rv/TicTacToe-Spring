package com.despina.TicTacToe.Storage;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.GameStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class GameStorage {

    HashMap<String, Game> games = new HashMap<>();

    public Optional<Game> getGame(String gameID) {
        return games.containsKey(gameID) ? Optional.of(games.get(gameID)) : Optional.empty();
    }

    public void addGame(Game g1) {
        games.put(g1.getId(), g1);
    }

    public Game getRandomGame() {
        List<Game> collect = games.values().stream().filter(vl -> vl.getGameStatus().compareTo(GameStatus.NEW) == 0).collect(Collectors.toList());
        Random random = new Random();
        int randomIndex = random.nextInt(collect.size());
        return collect.get(randomIndex);
    }
}
