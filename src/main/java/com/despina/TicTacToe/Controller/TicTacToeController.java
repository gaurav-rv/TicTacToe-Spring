package com.despina.TicTacToe.Controller;

import com.despina.TicTacToe.Entity.Game;
import com.despina.TicTacToe.Entity.Move;
import com.despina.TicTacToe.Entity.Player;
import com.despina.TicTacToe.Exception.GameNotFound;
import com.despina.TicTacToe.Service.GameService;
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
    private GameService gameService;
    @Autowired
    private MovesService movesService;

    @PostMapping("/start/{size}")
    public Game startGame(@RequestBody Player player, @PathVariable("size") int sizeOfBoard) {

        return gameService.startGame(player, sizeOfBoard);
    }

    @PostMapping("/{gameId}")
    public Game connectToGameByID(@RequestBody Player player, @PathVariable("gameId") String gameID) {
        try {
            return gameService.connectToGameByID(player, gameID);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/random")
    public Game connectToRandomGame(@RequestBody Player player) {
        try {
            return gameService.connectToRandomGame(player);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public Game makeMove(@RequestBody Move move) throws Exception {
        try{
            movesService.makeMove(move);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameService.getGamebyID(move.getGameId());
    }
}
