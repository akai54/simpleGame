package simpleGame.core;

import java.util.Random;

import simpleGame.exception.ImpossibleActionException;
import simpleGame.util.Logger;

/**
 * Represents an instance of the simple game. Controls the board and the pawns.
 * 
 * @author Erwan Bousse
 */
public class Game {

    /**
     * The board of the game
     */
    private Board board;

    /**
     * Constructs a Game with a Board containing 2 pawns on a 4x4 board.
     */
    public Game() {
        board = new Board(2, 4, 4, new Random().nextInt(4), new Random().nextInt(4));
    }

    /**
     * The game is over if there is only one pawn left or if a pawn possesses 5 gold
     * or more.
     * 
     * @return true if the game is over
     */
    public boolean isGameOver() {
        return (board.numberOfPawns() == 1) || (board.maxGold() >= 3);
    }

    /**
     * Show the board.
     */
    public String toString() {
        return board.toString();
    }

    /**
     * Play a round by making the current pawn act in specific direction
     * 
     * If the target position contains a pawn, then the "attack" method is called on
     * this pawn. If the target position is empty, then the "move" method is called
     * this position.
     * 
     * Then, after the action, the current pawn is changed to the next pawn.
     * 
     * @param direction The direction in which the pawn must act.
     * @throws ImpossibleActionException If the action is impossible (target square
     *                                   out of the board, or target square too far,
     *                                   or attack an empty square, or move to an
     *                                   occupied square, ).
     */
    public void playRound(Direction direction) throws ImpossibleActionException {

        Pawn currentPawn = this.board.getCurrentPawn();

        Position nextPosition = currentPawn.getPosition().getPositionNextTo(direction);

        switch (this.board.getStatusOfSquare(nextPosition)) {
        case OUT_OF_BOARD:
            throw new ImpossibleActionException("You cannot act in that direction, it is out of the board!");
        case EMPTY:
            currentPawn.move(nextPosition);
        case OCCUPIED:
            currentPawn.attack(nextPosition);
            break;
        }

        board.newTurn();

    }

}
