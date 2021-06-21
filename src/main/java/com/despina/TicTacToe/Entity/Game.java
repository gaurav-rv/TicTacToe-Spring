package com.despina.TicTacToe.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Game<Player> {
    private int[][] board;
    private String id;
//    private Player p1;
//    private Player p2;
    private GameStatus gameStatus;
    private Markers winner;
    private int[] row;
    private int[] col;
    private Player nextTurnPlayer;
    private int nextTurn;

    @Singular
    private List<Player> connectedPlayers;
}



