package tictactoeGame.services;

import tictactoeGame.exceptions.InvalidCellChosenException;
import tictactoeGame.models.*;

import java.util.Collections;
import java.util.List;

public class GameService {

    private BoardService boardService;

    public GameService(BoardService boardService) {
        this.boardService = boardService;
    }

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

    public Game undoMove(int undoCount, Game game){
        List<Board> playedBoards = game.getPlayedBoards();
        List<Move> moveList = game.getMoves();
        int movesPlayed = moveList.size();
        moveList = moveList.subList(0,movesPlayed - undoCount);
        playedBoards = playedBoards.subList(0, movesPlayed - undoCount);
        game.setBoard(playedBoards.getLast());
        //game.setMoves(moveList);
        //System.out.println("printing test board");
        //boardService.printBoard(game.getBoard());
        return game;
    }

    public void replay(Game game){
        List<Board> playedBoards = game.getPlayedBoards();
        for(Board board : playedBoards){
            boardService.printBoard(board);
            System.out.println("-------------------------");
        }
    }
}
