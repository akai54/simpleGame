package simpleGame.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import simpleGame.core.Board.SquareStatus;

public class TestBoard {

    private Board board;

    @BeforeEach
    public void init() {
        board = new Board(2, 4, 4, 3, 4);
    }

    @Test
    @DisplayName("Test du Constructeur du Board")
    public void testBoardConstruct() {
        /* Test position case bonus */
        assertEquals(board.getBonusSquare(), new Position(3,4));

        /* Test dimensions du plateau */
        assertEquals(board.getXSize(), 4);
        assertEquals(board.getYSize(), 4);
    }

    @Test
    public void testAddPawn() {
        //supprime tout les pions qui se sont initialisés aléatoirement sur la grille
        board.removeAllPawns();

        /* On s'assure que la position où il y aura pawn est vide et renvoi Null pour l'instant */
        assertNull(board.getSquareContent(new Position(3,3)));
        /* On ajoute pawn à la meme position d'en haut */
        Pawn pawn = new Pawn('E', 3, 3, board);
        board.addPawn(pawn);
        /* On vérifie que cette fois la meme position renvoi bien le pawn et non pas Null */
        assertEquals(pawn, board.getSquareContent(new Position(3,3)));
        assertNotNull(board.getSquareContent(new Position(3,3)));


        /* On cherche maintenant à s'assurer qu'on ne peux pas écraser un pion déjà présent */
        Pawn pawn2 = new Pawn('F', 3, 3, board);
        board.addPawn(pawn2);
        assertEquals(pawn, board.getSquareContent(new Position(3,3)));
    }

    @Test
    public void testGetSquareContent() {
        //supprime tout les pions qui se sont initialisés aléatoirement sur la grille
        board.removeAllPawns();

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
    public void removePawn() {
        //supprime tout les pions qui se sont initialisés aléatoirement sur la grille
        board.removeAllPawns();

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
    public void testIsBonusSquare() {
        assertTrue(board.isBonusSquare(new Position(3,4)));
        assertFalse(board.isBonusSquare(new Position(1,1)));
    }

    @Test
    public void testNumberOfPawns() {
        assertEquals(2, board.numberOfPawns());
    }

    @Test
    public void testRemoveAllPawns() {
        Board boardRemoveAllPawns = new Board(2,5,5,3,4);
        boardRemoveAllPawns.removeAllPawns();

        Pawn pawn1 = new Pawn('E', 4, 4, boardRemoveAllPawns);
        Pawn pawn2 = new Pawn('Z', 4, 1, boardRemoveAllPawns);

        boardRemoveAllPawns.addPawn(pawn1);
        boardRemoveAllPawns.addPawn(pawn2);

        assertEquals(pawn1, boardRemoveAllPawns.getSquareContent(new Position(4,4)));
        assertEquals(pawn2, boardRemoveAllPawns.getSquareContent(new Position(4,1)));

        boardRemoveAllPawns.removeAllPawns();

        assertNull(boardRemoveAllPawns.getSquareContent(new Position(4,4)));
        assertNull(boardRemoveAllPawns.getSquareContent(new Position(4,1)));
    }

    @Test
    public void testNewTurn() {
        Board boardNewTurn = new Board(3, 5, 5, 0, 0);
        Pawn pawn1 = boardNewTurn.getCurrentPawn();

        boardNewTurn.newTurn();
        Pawn pawn2 = boardNewTurn.getCurrentPawn();

        assertNotEquals(pawn1, pawn2);
    }

    @Test
    public void testSquareContentSprite() {
        Board boardContentSprite = new Board(1, 5, 5, 2, 2);
        boardContentSprite.removeAllPawns();

        Pawn pawn = new Pawn('A', 1, 1, boardContentSprite);
        boardContentSprite.addPawn(pawn);


        String pawn1 = boardContentSprite.squareContentSprite(new Position(1, 1));
        assertEquals("A", pawn1);


        String caseVide = boardContentSprite.squareContentSprite(new Position(0, 0));
        assertEquals(".", caseVide);

        /* Test pour une case bonus */
        String caseBonus = boardContentSprite.squareContentSprite(new Position(2, 2));
        assertEquals("\u001B[33m#\u001B[m", caseBonus);
    }

    @Test
    public void testGetStatusOfSquare() {
        //supprime tout les pions qui se sont initialisés aléatoirement sur la grille

        Board boardGetStatusOfSquare = new Board(1, 5, 5, 0, 0);
        boardGetStatusOfSquare.removeAllPawns();

        Pawn pawn = new Pawn('A', 2, 3, boardGetStatusOfSquare);
        boardGetStatusOfSquare.addPawn(pawn);

        /* Test pour une case occupée */
        assertEquals(SquareStatus.OCCUPIED, boardGetStatusOfSquare.getStatusOfSquare(new Position(2, 3)));

        /* Test pour une case vide */
        assertEquals(SquareStatus.EMPTY, boardGetStatusOfSquare.getStatusOfSquare(new Position(0, 0)));
        assertEquals(SquareStatus.EMPTY, boardGetStatusOfSquare.getStatusOfSquare(new Position(4, 4)));

        /* Test pour une case hors du plateau */
        assertEquals(SquareStatus.OUT_OF_BOARD, boardGetStatusOfSquare.getStatusOfSquare(new Position(2, 5)));
        assertEquals(SquareStatus.OUT_OF_BOARD, boardGetStatusOfSquare.getStatusOfSquare(new Position(5, 2)));
        assertEquals(SquareStatus.OUT_OF_BOARD, boardGetStatusOfSquare.getStatusOfSquare(new Position(2, -1)));
        assertEquals(SquareStatus.OUT_OF_BOARD, boardGetStatusOfSquare.getStatusOfSquare(new Position(-1, 2)));
    }

    @Test
    public void testGetCurrentPawn() {
        Board boardGetCurrentPawn = new Board(3, 5, 5, 0, 0);
        boardGetCurrentPawn.removeAllPawns();

        Pawn Pawn1 = new Pawn('A', 3, 0, boardGetCurrentPawn);
        Pawn Pawn2 = new Pawn('B', 3, 1, boardGetCurrentPawn);
        Pawn Pawn3 = new Pawn('C', 3, 2, boardGetCurrentPawn);

        boardGetCurrentPawn.addPawn(Pawn1);
        boardGetCurrentPawn.addPawn(Pawn2);
        boardGetCurrentPawn.addPawn(Pawn3);

        boardGetCurrentPawn.newTurn(); // On considère que c'est le premier tour, normalement non appellé

        assertEquals(Pawn1.getLetter(), boardGetCurrentPawn.getCurrentPawn().getLetter());

        boardGetCurrentPawn.newTurn();
        assertEquals(Pawn2.getLetter(), boardGetCurrentPawn.getCurrentPawn().getLetter());

        boardGetCurrentPawn.newTurn();
        assertEquals(Pawn3.getLetter(), boardGetCurrentPawn.getCurrentPawn().getLetter());

        boardGetCurrentPawn.newTurn();
        assertEquals(Pawn1.getLetter(), boardGetCurrentPawn.getCurrentPawn().getLetter());
    }
}