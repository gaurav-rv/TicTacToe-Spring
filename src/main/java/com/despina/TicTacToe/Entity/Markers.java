package com.despina.TicTacToe.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Markers {
    X(1), O(2);
    private Integer val;
}
