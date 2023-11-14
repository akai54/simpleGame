package simpleGame.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import simpleGame.util.CharUtil;
import simpleGame.util.StringColoring;
import simpleGame.util.StringColoring.GameColor;

/**
 * Describes the board on which Pawns can move. It is of rectangular shape, and
 * is made of squares. One of the square is a "bonus" square: it allows pawns to
 * be stronger.
 * 
 * @author Erwan Bousse
 *
 */
public class Board {

    /**
     * The number of squares on the x axis.
     */
    private int xSize;

    /**
     * The number of squares on the y axis.
     */
    private int ySize;

    /**
     * The Pawns that currently are on the board.
     */
    private List<Pawn> pawns;

    /**
     * The position of the bonus square
     */
    private Position bonusSquare;

    /**
     * The current pawn that must play.
     */
    private Pawn currentPawn;

    /**
     * Retrieve the width (x axis) of the board.
     * 
     * @return The width (x axis).
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * Retrieve the height (y axis) of the board.
     * 
     * @return The height (y axis).
     */
    public int getYSize() {
        return ySize;
    }

    /**
     * Construct a board, with a number of pawns, a size, and a bonus square. The
     * pawns are spread randomly on the board.
     * 
     * Each Pawn is named using a letter (A, B, C, etc.).
     * 
     * @param numberOfPawns The number of pawns.
     * @param sizeX         The number of squares on the x axis.
     * @param sizeY         The number of squares on the y axis.
     * @param xBonus        The x position of the bonus square.
     * @param yBonus        The y position of the bonus square.
     */
    public Board(int numberOfPawns, int sizeX, int sizeY, int xBonus, int yBonus) {
        Random random = new Random();
        this.xSize = sizeX;
        this.ySize = sizeY;
        this.bonusSquare = new Position(xBonus, yBonus);
        this.pawns = new ArrayList<Pawn>();
        for (int i = 1; i <= numberOfPawns; i++) {
            Pawn pawn = new Pawn(CharUtil.getCharForNumber(i + 1), random.nextInt(xSize), random.nextInt(ySize), this);
            this.addPawn(pawn);
        }

        currentPawn = pawns.get(0);
    }

    /**
     * Find the content of a square.
     * 
     * @param squarePosition The position to analyze.
     * @return The pawn found, or null if no pawn.
     */
    public Pawn getSquareContent(Position squarePosition) {
        for (Pawn p : pawns) {
            if (p.getPosition().equals(squarePosition)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Remove a pawn from the board.
     * If the pawn does not exist in the Board, does nothing.
     * 
     * @param pawn The pawn to remove.
     */
    public void removePawn(Pawn pawn) {
        pawns.remove(pawn);
    }

    private void addPawn(Pawn pawn) {
        if (getSquareContent(pawn.getPosition()) == null)
            this.pawns.add(pawn);
    }

    /**
     * Tell whether a square is bonus or not.
     * 
     * @param squarePosition The position of the square.
     * @return True if the square is bonus, false otherwise.
     */
    public boolean isBonusSquare(Position squarePosition) {
        return this.bonusSquare.equals(squarePosition);
    }

    /**
     * Find the number of pawns on the board.
     * 
     * @return The number of pawns on the board.
     */
    public int numberOfPawns() {
        return pawns.size();
    }

    /**
     * Compute the maximum amount of gold that a Pawn has.
     * 
     * @return The maximum amount of gold that a Pawn has.
     */
    public int maxGold() {
        int max = 0;
        for (Pawn p : pawns) {
            max = Math.max(max, p.getGold());
        }
        return max;
    }

    /**
     * Change the current pawn to the next pawn in the list.
     */
    public void newTurn() {
        if (pawns.size() == 1) {
            currentPawn = pawns.get(0);
        } else {
            currentPawn = this.pawns.get((this.pawns.indexOf(currentPawn) + 1) % this.pawns.size());
        }
    }

    /**
     * Compute what that should be displayed to represent the square or its content.
     * 
     * @param p the position of the square.
     * @return A String containing a single character, possibily colored using the
     *         `StringColoring` class. The character is '#' if the square is bonus
     *         and empty, '.' is the square is not bonus and empty, or simply the
     *         character associated to the pawn if the square is not empty. The
     *         color is YELLOW if the square is bonus, BLUE if it contains the
     *         current pawn, and GREEN if it contains the current pawn AND if the
     *         square is bonus.
     */
    public String squareContentSprite(Position p) {
        String result;
        Pawn content = getSquareContent(p);
        if (content == null) {
            if (isBonusSquare(p)) {
                result = StringColoring.colorString('#', GameColor.YELLOW);
            } else
                result = "⋅";
        } else {
            if (content == currentPawn) {
                if (isBonusSquare(p)) {
                    result = StringColoring.colorString(content.getLetter(), GameColor.GREEN);
                } else {
                    result = StringColoring.colorString(content.getLetter(), GameColor.BLUE);
                }
            } else {
                if (isBonusSquare(p)) {
                    result = StringColoring.colorString(content.getLetter(), GameColor.YELLOW);
                } else {
                    result = content.getLetter() + "";
                }
            }
        }
        return result;
    }

    /**
     * Computes a String that represents the whole board. Relies on
     * 'squareContentSprite' to print each square.
     * 
     * Example (without colors):
     * 
     * ⋅⋅⋅⋅ 
     * ⋅⋅A⋅ 
     * B⋅⋅⋅ 
     * #⋅⋅⋅
     * 
     */
    public String toString() {
        String result = "";

        for (int y = ySize - 1; y >= 0; y--) {
            for (int x = 0; x < xSize; x++) {
                Position squarePosition = new Position(x, y);
                result += squareContentSprite(squarePosition);
                if (x == xSize) {
                    result += '|';
                }
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Remove all the pawns.
     */
    public void removeAllPawns() {
        pawns.clear();
        currentPawn = null;
    }

    /**
     * The status of a square of the board:
     */
    public enum SquareStatus {
        OUT_OF_BOARD, EMPTY, OCCUPIED
    }

    /**
     * Retrieve the status of a square of the board.
     * 
     * @param p The position of the square to look at.
     * @return OUT_OF_BOARD is the square is out of board, EMPTY is the square is in
     *         the board but without a pawn, OCCUPIED if the square is in the board
     *         with a pawn.
     */
    public SquareStatus getStatusOfSquare(Position p) {
        int x = p.getX();
        int y = p.getY();
        if (y <= this.getYSize() && x <= this.getXSize() && y > 0 && x > 0) {
            Pawn content = this.getSquareContent(p);
            if (content == null) {
                return SquareStatus.EMPTY;
            } else {
                return SquareStatus.OCCUPIED;
            }
        } else {
            return SquareStatus.OUT_OF_BOARD;
        }
    }

    /**
     * Retrieve the current pawn.
     */
    public Pawn getCurrentPawn() {
        return this.currentPawn;
    }

}
