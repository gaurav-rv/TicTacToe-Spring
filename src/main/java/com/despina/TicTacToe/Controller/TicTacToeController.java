package com.despina.TicTacToe.Controller;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.Move;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Exception.GameNotFound;
import com.despina.TicTacToe.Service.MovesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/game")
public class TicTacToeController {

    @Autowired
    MovesService movesService;

    @PostMapping("/start/{size}")
    public Game startGame(@RequestBody Player player,@PathVariable("size") int sizeOfBoard) {

        return null;
    }

    @GetMapping("/{gameId}")
    public Game connectToGameByID(@PathVariable("gameId") String gameID) {

        return null;
    }

    @GetMapping("/random")
    public Game connectToRandomGame() {

        return null;
    }

    @PostMapping
    public Game makeMove(@RequestBody Move move) throws GameNotFound {

        return null;
    }
}
