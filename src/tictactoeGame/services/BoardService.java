package tictactoeGame.services;

import tictactoeGame.models.Board;
import tictactoeGame.models.Cell;

import java.util.List;

public class BoardService {

    public void printBoard(Board board){
        List<List<Cell>> cells = board.getCells();
        for(int i=0;i< board.getSize();i++){
            List<Cell> row = cells.get(i);
            for(Cell cell : row){
                if(cell.getPlayer()==null){
                    System.out.print("| | ");
                }
                else {
                    System.out.print("|" + cell.getPlayer().getSymbol() + "| ");
                }
            }
            System.out.println();
        }
    }
}
