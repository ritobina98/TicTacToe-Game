package tictactoeGame.services;

import tictactoeGame.models.Game;
import tictactoeGame.models.GameState;
import tictactoeGame.models.Player;

import java.util.Collections;
import java.util.List;

public class GameService {


    public Game createGame(int size, List<Player> players){
        Game newGame = Game.builder()
                .size(size)
                .players(players)
                .build();
        return newGame;
    }
    public Game startGame(Game game){
       game.setGameState(GameState.IN_PROGRESS);
        List<Player> players =  game.getPlayers();
        Collections.shuffle(players);
        game.setPlayers(players);
        return game;
    }
}
