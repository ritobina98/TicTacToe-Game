package tictactoeGame;

import tictactoeGame.controller.GameController;
import tictactoeGame.exceptions.GameDrawnException;
import tictactoeGame.models.Game;
import tictactoeGame.models.GameState;
import tictactoeGame.models.Move;
import tictactoeGame.models.Player;
import tictactoeGame.services.BoardService;
import tictactoeGame.services.GameService;
import tictactoeGame.services.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BoardService boardService = new BoardService();
        PlayerService playerService = new PlayerService();
        GameService gameService = new GameService();
        GameController gameController = new GameController(playerService, gameService);

            System.out.println("WELCOME TO TICTACTOE GAME");
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter the dimension of the game");
            int size = Integer.parseInt(sc.nextLine());
            List<Player> players = gameController.generatePlayerList(size);
            Game game = gameService.createGame(size, players);
            game = gameService.startGame(game);

            while (true) {
                int nextPlayerIndex = game.getNextMovePlayerIndex();
                Player currentplayer = game.getPlayers().get(nextPlayerIndex);
                System.out.println("Player to make a move: " + currentplayer.getName());
                Move move = gameController.createMove(currentplayer, game);
                boardService.printBoard(game.getBoard());
                try {
                    Player winner = gameController.checkWinner(game.getBoard(), move, game.getCheckWinnerUtil());
                    if (winner != null) {
                        game.setGameState(GameState.WINNER_DONE);
                        System.out.println("Winner is :" + winner.getName());
                        break;
                    }
                }catch (GameDrawnException ex){
                    System.out.println("Game is drawn, please restart");
                    break;
                }
                game.setNextMovePlayerIndex((game.getNextMovePlayerIndex() + 1) % players.size());
            }
    }
}