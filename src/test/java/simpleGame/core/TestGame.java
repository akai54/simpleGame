package simpleGame.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simpleGame.exception.ImpossibleActionException;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

    private Game game;
    private Board board;

    @BeforeEach
    public void init() {
        game = new Game();
        board = game.getBoard();
    }

    @Test
    @DisplayName("Constructeur")
    public void testGameConstructeur() {
        assertEquals(2, board.numberOfPawns());
        assertEquals(4, board.getXSize());
        assertEquals(4, board.getYSize());
    }

    @Test
    @DisplayName("isGameOver avec un pion restant")
    public void testIsGameOver1Pawn() {
        assertFalse(game.isGameOver());

        board.removePawn(board.getCurrentPawn());

        assertTrue(game.isGameOver());
    }

    @Test
    @DisplayName("isGameOver avec 3 pièces d'or")
    public void testIsGameOver3Gold() {
        assertFalse(game.isGameOver());

        board.getCurrentPawn().setGold(3);

        assertTrue(game.isGameOver());
    }

    @Test
    @DisplayName("Test de playRound avec un déplacement valide")
    public void testPlayRoundValidMove() throws ImpossibleActionException{
        board.removeAllPawns();
        Pawn pawn1 = new Pawn( 'A', 1, 1, board);
        Pawn pawn2 = new Pawn( 'B', 2, 1, board);

        board.addPawn(pawn1);
        board.addPawn(pawn2);

        board.newTurn();
        assertDoesNotThrow(() -> game.playRound(Direction.Right));
    }

    @Test
    @DisplayName("Test de playRound avec un déplacement invalide")
    public void testPlayRoundInvalidMove() throws ImpossibleActionException {
        board.removeAllPawns();
        Pawn pawn1 = new Pawn( 'A', 0, 1, board);
        Pawn pawn2 = new Pawn( 'B', 2, 3, board);

        board.addPawn(pawn1);
        board.addPawn(pawn2);

        board.newTurn();
        assertThrows(ImpossibleActionException.class, () -> {
            game.playRound(Direction.Left);
        });
    }

    @Test
    public void testToString(){
        assertEquals(board.toString(), game.toString());
    }
}