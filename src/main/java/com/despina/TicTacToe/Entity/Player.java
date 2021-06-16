package com.despina.TicTacToe.Entity;

import lombok.Data;

@Data
public class Player {
    int id;
    String name;

    /*
     int winSoFar;
    int lossSoFar;
    */

    public Player(int id){
        this.id = id;
    }
}
