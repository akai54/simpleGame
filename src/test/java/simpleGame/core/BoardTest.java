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
    void testAddPawn() {
        Board board = new Board(2,4,4,3,4);
        /* On s'assure que la position où il y aura pawn est vide et renvoi Null pour l'instant */
        assertNull(board.getSquareContent(new Position(3,3)));
        /* On ajoute pawn à la meme position d'en haut */
        Pawn pawn = new Pawn('E', 3, 3, board);
        board.addPawn(pawn);
        /* On vérifie que cette fois la meme position renvoi bien le pawn et non pas Null */
        assertEquals(pawn, board.getSquareContent(new Position(3,3)));
        assertNotNull(board.getSquareContent(new Position(3,3)));
    }

    @Test
    void testGetSquareContent() {
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
        Board board = new Board(2,4,4,3,4);
        /* On s'assure que la position où il y aura pawn est vide et renvoi Null pour l'instant */
        assertNull(board.getSquareContent(new Position(1,1)));

        /* On ajoute pawn à la meme position d'en haut */
        Pawn pawn = new Pawn('E', 1, 1, board);
        board.addPawn(pawn);

        /* On vérifie que cette fois la meme position renvoi bien le pawn et non pas Null */
        assertEquals(pawn, board.getSquareContent(new Position(1,1)));
        assertNotNull(board.getSquareContent(new Position(1,1)));

        /* On enlève le pion et on s'assure que cette position renvoi Null désormais */
        board.removePawn(pawn);
        assertNull(board.getSquareContent(new Position(1,1)));
    }

    @Test
    void testIsBonusSquare() {
        Board board = new Board(2,4,4,3,4);

        assertTrue(board.isBonusSquare(new Position(3,4)));
        assertFalse(board.isBonusSquare(new Position(1,1)));
    }

    @Test
    void numberOfPawns() {
        Board board = new Board(2,4,4,3,4);

        assertEquals(2, board.numberOfPawns());
    }

    @Test
    void getCurrentPawn() {
    }
}