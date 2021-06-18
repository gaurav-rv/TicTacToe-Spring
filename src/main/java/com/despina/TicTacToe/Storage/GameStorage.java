package com.despina.TicTacToe.Storage;

import com.despina.TicTacToe.Entity.Game;

import java.util.HashMap;

public class GameStorage {

    HashMap<String, Game> games;
    private static GameStorage instance;

    public static synchronized GameStorage getInstance(){
        if(instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }

    private GameStorage(){
        this.games = new HashMap<String, Game>();
    }

    public HashMap<String, Game> getGame(){
        return games;
    }

    public void addGame(Game g1){
        games.put(g1.getId(), g1);
    }
}
