package tictactoeGame.controller;

import tictactoeGame.exceptions.InvalidCellChosenException;
import tictactoeGame.exceptions.InvalidUndoCommandException;
import tictactoeGame.models.*;
import tictactoeGame.services.CheckWinnerUtil;
import tictactoeGame.services.GameService;
import tictactoeGame.services.PlayerService;
import tictactoeGame.services.RandomBotPlayingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private PlayerService playerService;
    private GameService gameService;
    private Scanner sc;
    public GameController(PlayerService playerService,GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
        sc = new Scanner(System.in);
    }

    public List<Player> generatePlayerList(int size){
        System.out.println("Please enter 1 for Bot, 0 for Human");
        int botResult = Integer.parseInt(sc.nextLine());
        Bot bot;
        List<Player> players = new ArrayList<>();
        if(botResult==1){
            bot = playerService.createBot("BOT",'$');
            size--;
            players.add(bot);
        }
            for (int i = 0; i < size - 1; i++) {
                System.out.println("Please enter the name for player : " + (i + 1));
                String name = sc.nextLine();
                //name = sc.nextLine();
                System.out.println("Please enter the character for the player : " + name);
                char symbol = sc.nextLine().charAt(0);
                players.add(playerService.createPlayer(name, symbol));
            }
            return players;
        }

        public Move createMove(Player player, Game game){
            if(player.getPlayerType().equals(PlayerType.HUMAN)) {
                System.out.println("Please enter the row number");
                int row = sc.nextInt();
                System.out.println("Please enter the col number ");
                int col = sc.nextInt();
                // TODO : validate the row and col range within board
                try {
                    Move move = gameService.executeMove(player, game, row, col);
                    return move;
                } catch (InvalidCellChosenException ex) {
                    // TODO: handle new entry logic
                }
            } else {
                Move move = RandomBotPlayingStrategy.makeMove(player, game);
                return move;
            }
            return null;
        }
        public Player checkWinner(Board board, Move move, CheckWinnerUtil checkWinnerUtil){
            return checkWinnerUtil.checkWinner(board,move);
        }

        public Game undoGame(int moves,Game game){
            if(moves>game.getMoves().size()){
                throw new InvalidUndoCommandException("Number of undo steps is invalid");
            }
            return gameService.undoMove(moves,game);
        }

        public void replay(Game game){
            gameService.replay(game);
        }
    }

