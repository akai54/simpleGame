package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import simpleGame.core.Board.SquareStatus;

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
    void testRemoveAllPawns() {
        Board board = new Board(2,5,5,3,4);
        Pawn pawn1 = new Pawn('E', 4, 4, board);
        Pawn pawn2 = new Pawn('Z', 4, 1, board);

        board.addPawn(pawn1);
        board.addPawn(pawn2);

        assertEquals(pawn1, board.getSquareContent(new Position(4,4)));
        assertEquals(pawn2, board.getSquareContent(new Position(4,1)));

        board.removeAllPawns();

        assertNull(board.getSquareContent(new Position(4,4)));
        assertNull(board.getSquareContent(new Position(4,1)));
    }

    @Test
    void testNewTurn() {
        Board board = new Board(3, 5, 5, 0, 0);
        Pawn pawn1 = board.getCurrentPawn();

        board.newTurn();
        Pawn pawn2 = board.getCurrentPawn();

        assertNotEquals(pawn1, pawn2);
    }

    @Test
    public void testSquareContentSprite() {
        Board board = new Board(1, 5, 5, 2, 2);
        Pawn pawn = new Pawn('A', 1, 1, board);
        board.addPawn(pawn);


        String pawn1 = board.squareContentSprite(new Position(1, 1));
        assertEquals("A", pawn1);


        String caseVide = board.squareContentSprite(new Position(0, 0));
        assertEquals("⋅", caseVide);

        /* Test pour une case bonus */
        String caseBonus = board.squareContentSprite(new Position(2, 2));
        assertEquals("\u001B[33m#\u001B[m", caseBonus);
    }

    @Test
    public void testToString() {
        Board board = new Board(1, 3, 3, 1, 1); // Un plateau 3x3 avec une case bonus
        Pawn pawn = new Pawn('A', 0, 0, board);
        board.addPawn(pawn);

        // Générez la chaîne attendue en fonction de la disposition spécifique du plateau
        String expected = "⋅⋅⋅\n⋅\u001B[33m#\u001B[m⋅\nA⋅⋅\n";
        assertEquals(expected, board.toString());
    }

    @Test
    public void testGetStatusOfSquare() {
        Board board = new Board(1, 5, 5, 0, 0);
        Pawn pawn = new Pawn('A', 2, 3, board);
        board.addPawn(pawn);

        /* Test pour une case occupée */
        assertEquals(SquareStatus.OCCUPIED, board.getStatusOfSquare(new Position(2, 3)));

        /* Test pour une case vide */
        assertEquals(SquareStatus.EMPTY, board.getStatusOfSquare(new Position(1, 1)));

        /* Test pour une case hors du plateau */
        assertEquals(SquareStatus.OUT_OF_BOARD, board.getStatusOfSquare(new Position(6, 6))); // En supposant que le plateau fait 5x5
    }
}