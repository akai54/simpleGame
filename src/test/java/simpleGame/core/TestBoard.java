package simpleGame.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import simpleGame.core.Board.SquareStatus;
import simpleGame.util.StringColoring;
import simpleGame.util.StringColoring.GameColor;

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
        assertEquals(board.getBonusSquare(), new Position(2,3));

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
        assertTrue(board.isBonusSquare(new Position(2,3)));
        assertFalse(board.isBonusSquare(new Position(3,4)));
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
    @DisplayName("S'assure que le pion courrant roule à chaque changement de tour")
    public void testNewTurn() {
        board.removeAllPawns();

        Pawn pawn1 = new Pawn('E', 4, 4, board);
        Pawn pawn2 = new Pawn('Z', 4, 1, board);
        Pawn pawn3 = new Pawn('D', 0, 0, board);

        board.addPawn(pawn1);
        board.setCurrentPawn(pawn1);

        //Si il n'y a qu'un seul pion, le nouveau pion reste le même
        board.newTurn();
        assertEquals(pawn1, board.getCurrentPawn());
        
        //mais avec plusieurs pion, il y a un roulement
        board.addPawn(pawn2);
        board.addPawn(pawn3);

        board.newTurn();
        assertEquals(pawn2, board.getCurrentPawn());

        board.newTurn();
        assertEquals(pawn3, board.getCurrentPawn());

        board.newTurn();
        assertEquals(pawn1, board.getCurrentPawn());
    }

    @Test
    public void testSquareContentSprite() {
        Board boardContentSprite = new Board(1, 5, 5, 2, 3);
        boardContentSprite.removeAllPawns();

        Pawn pawn = new Pawn('A', 1, 1, boardContentSprite);
        boardContentSprite.addPawn(pawn);


        String pawn1 = boardContentSprite.squareContentSprite(new Position(1, 1));
        assertEquals("A", pawn1);


        String caseVide = boardContentSprite.squareContentSprite(new Position(0, 0));
        assertEquals(".", caseVide);

        /* Test pour une case bonus vide*/
        String caseBonus = boardContentSprite.squareContentSprite(new Position(1, 2));
        assertEquals(StringColoring.colorString("#", GameColor.YELLOW), caseBonus);
    }

    @Test
    @DisplayName("Test pawn's coloration on the board")
    public void testSquareContentSpritePawn(){
        Board boardContentSprite = new Board(1, 5, 5, 2, 3);
        boardContentSprite.removeAllPawns();

        Pawn pawn1 = new Pawn('A', 1, 2, boardContentSprite);
        Pawn pawn2 = new Pawn('D', 0, 0, boardContentSprite);
        
        boardContentSprite.addPawn(pawn1);
        boardContentSprite.addPawn(pawn2);

        // Un pion inactif sur une case Bonus dois être YELLOW
        String string1 = boardContentSprite.squareContentSprite(new Position(1, 2));
        assertEquals(StringColoring.colorString("A", GameColor.YELLOW), string1);


        // Un pion ACTIF sur une case Bonus dois être VERT
        boardContentSprite.setCurrentPawn(pawn1);

        String string2 = boardContentSprite.squareContentSprite(new Position(1, 2));
        assertEquals(StringColoring.colorString("A", GameColor.GREEN), string2);


        // Un pion ACTIF sur une case non-Bonus dois être BLUE
        boardContentSprite.setCurrentPawn(pawn2);

        String string3 = boardContentSprite.squareContentSprite(new Position(0, 0));
        assertEquals(StringColoring.colorString("D", GameColor.BLUE), string3);
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

    @Test
    @DisplayName("S'assure que la sortie texte soit correcte")
    public void testToString(){
        board.removeAllPawns();

        Pawn Pawn1 = new Pawn('A', 2, 0, board);
        Pawn Pawn2 = new Pawn('B', 3, 1, board);
        Pawn Pawn3 = new Pawn('C', 0, 2, board);

        board.addPawn(Pawn1);
        board.addPawn(Pawn2);
        board.addPawn(Pawn3);

        String diese = StringColoring.colorString("#", GameColor.YELLOW);
        String texteAttendu =  ".." + diese + ".\nC...\n...B\n..A.\n";

        assertEquals(texteAttendu, board.toString());
    }
}