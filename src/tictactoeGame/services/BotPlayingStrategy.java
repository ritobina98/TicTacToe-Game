package tictactoeGame.services;

import tictactoeGame.models.Game;
import tictactoeGame.models.Move;
import tictactoeGame.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Player player, Game game);
}
