package tictactoeGame.services;

import tictactoeGame.exceptions.GameDrawnException;
import tictactoeGame.models.Board;
import tictactoeGame.models.Move;
import tictactoeGame.models.Player;

import java.util.*;

public class CheckWinnerUtil {
    private List<HashMap<Character,Integer>> rowHashMaps;
    private List<HashMap<Character,Integer>> colHashMaps;
    private HashMap<Character,Integer> topLeftDiagonalMap;
    private HashMap<Character,Integer> topRightDiagonalMap;
    private HashMap<Character,Integer> cornerHashMap;
    private  int size;
    public CheckWinnerUtil(int size){
        this.size = size;
        rowHashMaps = new ArrayList<>();
        colHashMaps = new ArrayList<>();
        for(int i=0;i<size;i++){
            rowHashMaps.add(new HashMap<>());
            colHashMaps.add(new HashMap<>());
        }
        topLeftDiagonalMap = new HashMap<>();
        topRightDiagonalMap = new HashMap<>();
        cornerHashMap = new HashMap<>();
    }
    public Player checkWinner(Board board, Move currentMove){
        char symbol = currentMove.getPlayer().getSymbol();
        int row = currentMove.getCell().getRow();
        int col = currentMove.getCell().getCol();
        //update row and col hashmaps
        HashMap<Character,Integer> rowHashMap = rowHashMaps.get(row);
        HashMap<Character,Integer> colHashMap = colHashMaps.get(col);
        rowHashMap.put(symbol,rowHashMap.getOrDefault(symbol,0)+1);
        colHashMap.put(symbol,colHashMap.getOrDefault(symbol,0)+1);

        //update diagonal hashmaps
        if(row == col){
            topLeftDiagonalMap.put(symbol,topLeftDiagonalMap.getOrDefault(symbol,0)+1);
        }
        if(row + col == (size - 1)){
            topRightDiagonalMap.put(symbol,topRightDiagonalMap.getOrDefault(symbol,0)+1);
        }
        //update corner hashmaps
        if((row == 0 || row== size-1) && (col==0 || col==size-1)){
            cornerHashMap.put(symbol,cornerHashMap.getOrDefault(symbol,0)+1);
        }

        if(cornerHashMap.getOrDefault(symbol,0)==4
                 || topLeftDiagonalMap.getOrDefault(symbol,0)==size
                || topRightDiagonalMap.getOrDefault(symbol,0)==size
                || rowHashMap.get(symbol)==size
                ||colHashMap.get(symbol)==size){
            return currentMove.getPlayer();
        }
        if(checkDraw()){
            throw new GameDrawnException("No more winner possible");
        }

        return null;
    }

    private boolean checkDraw(){
        for(HashMap<Character,Integer> map : rowHashMaps){
            if(map.size()<=1)
                return false;
        }
        for(HashMap<Character,Integer> map : colHashMaps){
            if(map.size()<=1)
                return false;
        }
        if((topLeftDiagonalMap.size()<=1) || (topRightDiagonalMap.size()<=1) || (cornerHashMap.size()<=1))
            return false;
        return true;
    }
}
