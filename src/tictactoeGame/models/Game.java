package tictactoeGame.models;

import tictactoeGame.exceptions.InvalidBoardSizeException;
import tictactoeGame.exceptions.InvalidNoOfPlayersException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private Player winner;
    private List<Move> moves;
    private List<Board> playedBoards;

    private Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.gameState = GameState.YET_TO_START;
        this.nextMovePlayerIndex = 0;
        //this.winner = winner;
        this.moves = new ArrayList<>();
        this.playedBoards = new ArrayList<>();
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int size;
        private List<Player> players;

        public Builder size(int size) {
            this.size = size;
            return  this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validate()
        {
            //size validation
            if(size>10 || size<3){
                throw new InvalidBoardSizeException("Board size should be between3 to 10");
            }
            //players validation
            //player size should be size-1
            if(players.size()!=(size-1)){
                throw new InvalidNoOfPlayersException("Players should be size-1");
            }
        }
        public Game build(){
            validate();
            Board gameBoard = new Board(size);
            return new Game(gameBoard,players);
        }
    }
}
