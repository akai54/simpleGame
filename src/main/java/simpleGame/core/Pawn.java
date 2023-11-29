package simpleGame.core;

import simpleGame.core.Board.SquareStatus;
import simpleGame.exception.ImpossibleActionException;
import simpleGame.util.Logger;

public class Pawn {

    /**
     * Position of the pawn in the board.
     */
    private Position position;

    /**
     * The board on which the pawn is located
     */
    private Board board;

    /**
     * The remaining number of hitpoints of the pawn
     */
    private int hitpoints;

    /**
     * The collected gold of the pawn.
     */
    private int gold;

    /**
     * The char that represents the pawn.
     */
    private char letter;

    /**
     * Retrieve the position of the pawn.
     * 
     * @return A copy of the position of the pawn.
     */
    public Position getPosition() {
        return this.position.copy();
    }

    /**
     * Retrieve the letter of the pawn.
     *
     * @return The letter of the pawn.
     */
    public char getLetter() {
        return letter;
    }

    /** ADDED BY TESTERS
     * Retrieve the amount of hitpoint of the pawn.
     *
     * @return The amount od hitpoint of the pawn.
     */
    public int getHitpoints() {
        return hitpoints;
    }

    /**
     * Retrieve the amount of gold of the pawn.
     * 
     * @return The amount of gold of the pawn.
     */
    public int getGold() {
        return gold;
    }

    /**
     * Create a Pawn with 2 hitpoints and 0 gold.
     * 
     * @param n     The letter that represents the pawn.
     * @param x     Position on the x axis
     * @param y     Position on the y axis
     * @param board The board on which the pawn is located
     */
    public Pawn(char n, int x, int y, Board board) {
        this.letter = n;
        this.position = new Position(x, y);
        this.board = board;
        this.hitpoints = 2;
        this.gold = 0;
    }

    /**
     * Move the pawn to a new position.
     * 
     * @param p The position where the pawn should move.
     * @throws ImpossibleActionException If the target position is out of bound, or occupied, or too far.
     */
    public void move(Position p) throws ImpossibleActionException {
        if (this.position.isNextTo(p)) {
            SquareStatus status = this.board.getStatusOfSquare(p);
            switch (status) {
            case OUT_OF_BOARD:
                throw new ImpossibleActionException("Square out of the board, cannot move there.");
            case EMPTY:
                this.position = p.copy();
                break;
            case OCCUPIED:
                throw new ImpossibleActionException("Square already occupied, cannot move there.");
            }
        } else {
            throw new ImpossibleActionException("Square is too far, cannot move there.");
        }
    }

    /**
     * To make the Pawn lose hitpoints. If the pawn reaches 0 hitpoints, it is
     * removed from the board.
     * 
     * @param i The number of hitpoints to lose.
     */
    public void suffer(int i) {
        Logger.log(this.letter + " loses " + i + " hitpoints.");
        hitpoints = hitpoints - i;
        if (this.isDead()) {
            this.board.removePawn(this);
            Logger.log(this.letter + " is dead.");
        }
    }

    /**
     * To know whether a pawn is dead or not.
     * A pawn is considered dead when it reaches zero hitpoints.
     * 
     * @return True if the pawn is dead, false otherwise.
     */
    public boolean isDead() {
        return this.hitpoints == 0;
    }

    /**
     * Make the pawn attack another position. If any, the enemy pawn should suffer 1
     * damage, but it should suffer 2 damages if the current pawn is on a bonus
     * square.
     * 
     * If the enemy pawn dies, then the current pawn obtains 1 gold.
     * 
     * @param enemy The attacked pawn.
     * @throws ImpossibleActionException If the target position is too far or empty.
     */
    public void attack(Position p) throws ImpossibleActionException {

        if (this.position.isNextTo(p)) {
            Pawn enemy = this.board.getSquareContent(p);
            if (enemy == null) {
                throw new ImpossibleActionException("There is no enemy pawn in that position, cannot attack.");
            } else {
                Logger.log(this.letter + " attacks!\n");
                if (this.board.isBonusSquare(this.position))
                    enemy.suffer(2);
                else
                    enemy.suffer(1);
                if (enemy.isDead())
                    gold++;
            }
        } else {
            throw new ImpossibleActionException("Square is too far, cannot attack.");
        }

    }

}
