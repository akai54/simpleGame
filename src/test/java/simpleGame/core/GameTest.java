package simpleGame.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simpleGame.core.Pawn;
import simpleGame.exception.ImpossibleActionException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void init() {
         game = new Game();
    }

    @Test
    @DisplayName("Constructeur")
    void testGameConstructeur() {
        assertEquals(2, game.board.numberOfPawns());
        assertEquals(4, game.board.getXSize());
        assertEquals(4, game.board.getYSize());
    }

    @Test
    @DisplayName("isGameOver avec un pion restant")
    void testIsGameOver1Pawn() {
        assertFalse(game.isGameOver());

        game.board.removePawn(game.board.getCurrentPawn());

        assertTrue(game.isGameOver());
    }

    @Test
    @DisplayName("isGameOver avec 3 pièces d'or")
    void testIsGameOver3Gold() {
        assertFalse(game.isGameOver());

        game.board.getCurrentPawn().setGold(3);

        assertTrue(game.isGameOver());
    }

    @Test
    @DisplayName("Test de playRound avec déplacement valide")
    void testPlayRoundValidMove() throws ImpossibleActionException {
        Pawn pawn1 = new Pawn( 'a', 0, 1, game.board);
        Pawn pawn2 = new Pawn( 'b', 1, 1, game.board);

        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn1);
        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn2);
        assertDoesNotThrow(() -> game.playRound(Direction.Right));
    }
}