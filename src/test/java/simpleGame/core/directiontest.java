package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class directiontest {

    @Test
    @DisplayName("Tester les 4 valeurs")
    public void testDirectionValues() {
        Direction[] directions = Direction.values();

        /* Verifier que chaque direction renvoie la bonne valeur */
        assertTrue(containsDirection(directions, Direction.Up));
        assertTrue(containsDirection(directions, Direction.Down));
        assertTrue(containsDirection(directions, Direction.Left));
        assertTrue(containsDirection(directions, Direction.Right));

        /* Verifier le nombre total de directions */
        assertEquals(4, directions.length);
    }

    private boolean containsDirection(Direction[] directions, Direction direction) {
        for (Direction dir : directions) {
            if (dir == direction) {
                return true;
            }
        }
        return false;
    }
}