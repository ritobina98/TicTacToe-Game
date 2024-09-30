package tictactoeGame.exceptions;

public class InvalidUndoCommandException extends RuntimeException{
    public InvalidUndoCommandException() {
    }

    public InvalidUndoCommandException(String message) {
        super(message);
    }
}
