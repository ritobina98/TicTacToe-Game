package tictactoeGame.models;

import tictactoeGame.exceptions.InvalidBoardSizeException;
import tictactoeGame.exceptions.InvalidNoOfPlayersException;
import tictactoeGame.services.CheckWinnerUtil;

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
    private CheckWinnerUtil checkWinnerUtil;

    private Game(Board board, List<Player> players,CheckWinnerUtil checkWinnerUtil) {
        this.board = board;
        this.players = players;
        this.gameState = GameState.YET_TO_START;
        this.nextMovePlayerIndex = 0;
        //this.winner = winner;
        this.moves = new ArrayList<>();
        this.playedBoards = new ArrayList<>();
        this.checkWinnerUtil = checkWinnerUtil;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Board> getPlayedBoards() {
        return playedBoards;
    }

    public void setPlayedBoards(List<Board> playedBoards) {
        this.playedBoards = playedBoards;
    }

    public CheckWinnerUtil getCheckWinnerUtil() {
        return checkWinnerUtil;
    }

    public void setCheckWinnerUtil(CheckWinnerUtil checkWinnerUtil) {
        this.checkWinnerUtil = checkWinnerUtil;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int size;
        private List<Player> players;
        private CheckWinnerUtil checkWinnerUtil;

        public Builder size(int size) {
            this.size = size;
            return  this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }
        public Builder checkWinnerUtil(CheckWinnerUtil checkWinnerUtil) {
            this.checkWinnerUtil = checkWinnerUtil;
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
            return new Game(gameBoard,players,checkWinnerUtil);
        }
    }
}
