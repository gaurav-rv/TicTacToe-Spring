package com.despina.TicTacToe.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Markers {
    X(1), O(-1);
    private Integer val;

    public static boolean contains(int s)
    {
        return Stream.of(Markers.values()).anyMatch(val -> val.equals(s));
    }

}
