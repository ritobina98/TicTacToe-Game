package tictactoeGame.exceptions;

public class InvalidNoOfPlayersException  extends RuntimeException{
    public InvalidNoOfPlayersException() {
    }

    public InvalidNoOfPlayersException(String message) {
        super(message);
    }
}
