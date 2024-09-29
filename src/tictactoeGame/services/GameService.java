package tictactoeGame.services;

import tictactoeGame.exceptions.InvalidCellChosenException;
import tictactoeGame.models.*;

import java.util.Collections;
import java.util.List;

public class GameService {


    public Game createGame(int size, List<Player> players){
        CheckWinnerUtil checkWinnerUtil = new CheckWinnerUtil(size);
        Game newGame = Game.builder()
                .size(size)
                .players(players)
                .checkWinnerUtil(checkWinnerUtil)
                .build();
        return newGame;
    }
    public Game startGame(Game game){
       game.setGameState(GameState.IN_PROGRESS);
        List<Player> players =  game.getPlayers();
        Collections.shuffle(players);
        return game;
    }

    public Move executeMove(Player player, Game game, int row, int col){
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if(cell.getCellState()!= CellState.EMPTY){
            throw new InvalidCellChosenException("Cell is already full");
        }
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(player);
        Move move = new Move(cell,player);
        game.getMoves().add(move);
        game.getPlayedBoards().add(game.getBoard().clone());
        return move;
    }
}
