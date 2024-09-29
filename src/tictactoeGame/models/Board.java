package tictactoeGame.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Cell>> cells;
    int size;

    public Board(int size){
        this.size = size;
        cells=new ArrayList<>();
        for(int i=0;i<size;i++){
            cells.add(new ArrayList<>());
            for(int j=0;j<size;j++){
                cells.get(i).add(new Cell(i,j));
            }
        }
    }
    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Board clone(){
        Board board = new Board(this.size);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board.getCells().get(i).add(j,this.cells.get(i).get(j).clone());
            }
        }
        return board;
    }
}
