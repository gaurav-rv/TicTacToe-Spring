package com.despina.TicTacToe.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game<Player> {
    private int[][] board;
    private String id;
    private Player p1;
    private Player p2;
    private GameStatus gameStatus;
    private Markers winner;
    private int[] row;
    private int[] col;
}



