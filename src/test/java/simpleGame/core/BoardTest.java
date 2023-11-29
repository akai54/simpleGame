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
        Board board = new Board(2,4,4,3,4);
        Pawn pawn = new Pawn('E', 3, 4, board);

        /* On suppose que addPawn() ajoute un pion au plateau correctement */
        board.addPawn(pawn);

        /* Test que les coordonnées renvoyées par getSquareContent() corresponde à pawn */
        assertEquals(pawn, board.getSquareContent(new Position(3,4)));
        /* les coordonnées de la case où se trouve pawn n'est pas equivalent à Null */
        assertNotNull(board.getSquareContent(new Position(3,4)));
        /* les coordonnées où, il n'y a pas de pions, renvoi Null */
        assertNull(board.getSquareContent(new Position(2,2)));
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