package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simpleGame.core.Game;
import simpleGame.core.Board;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    @DisplayName("Constructeur")
    void testGameConstructeur() {
        Game game = new Game();

        assertEquals(2, game.board.numberOfPawns());
        assertEquals(4, game.board.getXSize());
        assertEquals(4, game.board.getYSize());
    }
}