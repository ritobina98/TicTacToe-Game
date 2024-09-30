package tictactoeGame;

import tictactoeGame.controller.GameController;
import tictactoeGame.exceptions.GameDrawnException;
import tictactoeGame.models.*;
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
        GameService gameService = new GameService(boardService);
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
                if(!currentplayer.getPlayerType().equals(PlayerType.BOT)) {
                    System.out.println("Do you want to undo to  certain step ? 1 for Yes, 0 for No");
                    int undo = sc.nextInt();
                    if (undo == 1) {
                        System.out.println("Please enter the no of steps you want to go back :");
                        int undoCount = sc.nextInt();
                        game = gameController.undoGame(undoCount, game);
                    }
                }
                Move move = gameController.createMove(currentplayer, game);
                boardService.printBoard(game.getBoard());
                try {
                    Player winner = gameController.checkWinner(game.getBoard(), move, game.getCheckWinnerUtil());
                    if (winner != null) {
                        game.setGameState(GameState.WINNER_DONE);
                        System.out.println("Winner is :" + winner.getName());
                        System.out.println("Do you want a replay ? 1 for Yes, 0 for No");
                        int replay = sc.nextInt();
                        if(replay==1){
                            gameController.replay(game);
                        }
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