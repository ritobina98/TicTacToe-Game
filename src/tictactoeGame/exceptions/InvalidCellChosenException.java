package tictactoeGame.exceptions;

public class InvalidCellChosenException extends RuntimeException{
    public InvalidCellChosenException() {
    }

    public InvalidCellChosenException(String message) {
        super(message);
    }
}
