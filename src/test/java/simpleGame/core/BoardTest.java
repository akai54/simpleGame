package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    @DisplayName("Test Constructeur")
    void testBoardConstruct() {
        Board board = new Board(2,4,4,3,4);

        /* Test position case bonus */
        assertEquals(board.getBonusSquare(), new Position(3,4));

        /* Test dimensions du plateau */
        assertEquals(board.getXSize(), 4);
        assertEquals(board.getYSize(), 4);
    }

    @Test
    void getSquareContent() {
    }

    @Test
    void removePawn() {
    }

    @Test
    void isBonusSquare() {
    }

    @Test
    void numberOfPawns() {
    }

    @Test
    void getCurrentPawn() {
    }
}