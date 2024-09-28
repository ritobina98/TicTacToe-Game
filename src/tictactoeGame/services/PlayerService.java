package tictactoeGame.services;

import tictactoeGame.exceptions.DuplicateSymbolException;
import tictactoeGame.models.Player;
import tictactoeGame.models.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private static int counter =1;
    private HashSet<Character> symbolSet;

    public PlayerService(){
        this.symbolSet=new HashSet<>();
    }
    public Player createPlayer(String name,char symbol){
        if (symbolSet.contains(symbol)) {
            throw new DuplicateSymbolException("Duplicate symbol is not allowed");
        }
        symbolSet.add(symbol);
        return new Player(
                counter++,
                name,
                symbol,
                PlayerType.HUMAN
        );
    }


}
