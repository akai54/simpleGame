package simpleGame.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void testBoardConstruct() {
        Board board = new Board(2,4,4,3,4);

        /* Test position case bonus */
        assertEquals(board.getBonusSquare(), new Position(3,4));
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