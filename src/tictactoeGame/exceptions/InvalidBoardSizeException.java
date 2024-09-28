package tictactoeGame.exceptions;

public class InvalidBoardSizeException extends RuntimeException{
    public InvalidBoardSizeException() {
    }

    public InvalidBoardSizeException(String message) {
        super(message);
    }
}
