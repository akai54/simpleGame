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
    @DisplayName("Test de playRound avec un déplacement valide")
    void testPlayRoundValidMove() {
        Pawn pawn1 = new Pawn( 'A', 1, 1, game.board);
        Pawn pawn2 = new Pawn( 'B', 2, 1, game.board);

        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn1);
        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn2);
        assertDoesNotThrow(() -> game.playRound(Direction.Right));
    }

    @Test
    @DisplayName("Test de playRound avec un déplacement invalide")
    void testPlayRoundInvalidMove() throws ImpossibleActionException {
        Pawn pawn1 = new Pawn( 'A', 0, 1, game.board);
        Pawn pawn2 = new Pawn( 'B', 2, 3, game.board);

        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn1);
        game.board.removePawn(game.board.getCurrentPawn());
        game.board.addPawn(pawn2);
        assertThrows(ImpossibleActionException.class, () -> {
            game.playRound(Direction.Left);
        });
    }
}