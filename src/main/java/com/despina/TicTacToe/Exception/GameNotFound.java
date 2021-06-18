package com.despina.TicTacToe.Exception;

public class GameNotFound extends Exception {
    String msg;

    public GameNotFound(String msg ) {this.msg = msg;}

    public String getMessage(){
        return this.msg;
    }

}
