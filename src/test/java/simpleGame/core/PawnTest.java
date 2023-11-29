package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simpleGame.exception.ImpossibleActionException;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("Constructor correctly initialized")
    void testPawnConstructor() {
        Board board = new Board(1,5,5, 2, 2);
        Pawn pawn = new Pawn( 'c', 1, 1, board);

        assertEquals(pawn.getLetter(), 'c');
        assertEquals(pawn.getGold(), 0);
        //assertEquals(pawn.getHitpoints(), 2);
        assertTrue(pawn.getPosition().equals(new Position(1,1)));
    }

    @Test
    @DisplayName("Test if the move function works as intended")
    void testMove() throws ImpossibleActionException{
        Board board = new Board(2,5,5, 2, 2);
        Pawn pawn0 = new Pawn( 'c', 0, 1, board);
        Position pos0 = new Position(0,0);
        Pawn pawn1 = new Pawn( 'c', 3, 4, board);
        Position pos1 = new Position(4,4);

        //test lower corner
        pawn0.move(pos0);
        assertEquals(pawn0.getPosition(), pos0);

        //test higher corner
        pawn1.move(pos1);
        assertEquals(pawn1.getPosition(), pos1);
    }

    @Test
    @DisplayName("Test if the move function returns ImpossibleActionException")
    void testMoveException() throws ImpossibleActionException{
        Board board = new Board(3,5,5, 2, 2);
        Pawn pawn0 = new Pawn( 'c', 0, 0, board);
        Pawn pawn1 = new Pawn( 'c', 4, 4, board);

        //when out of the grid
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(-1,0)));
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(0,-1)));
        assertThrows(ImpossibleActionException.class, () -> pawn1.move(new Position(5,4)));
        assertThrows(ImpossibleActionException.class, () -> pawn1.move(new Position(4,5)));

        //when the new position is not adjacent
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(1,1)));

        //when the Position is already taken
        Pawn pawn3 = new Pawn( 'c', 3, 4, board);
        assertThrows(ImpossibleActionException.class, () -> pawn0.move(new Position(3,4)));
    }

    


    /*
     * Move the pawn to a new position.
     *
     * @param p The position where the pawn should move.
     * @throws ImpossibleActionException If the target position is out of bound, or occupied, or too far.
     */
}
