package com.despina.TicTacToe.Entity;

import lombok.Builder;
import lombok.Data;

@Data
public class Move {
    private Markers markers;
    private int x;
    private int y;
    private String gameId;
}
