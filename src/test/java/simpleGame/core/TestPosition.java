package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPosition {

    @Test
    @DisplayName("Constructor correctly initialized")
    public void testPositionConstructor() {
        Position position = new Position(3,4);
        assertEquals(3, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    @DisplayName("create object with same coordinates")
    public void testCopy() {
        Position originalPosition = new Position(3,4);
        Position copied = originalPosition.copy();

        assertEquals(originalPosition.getX(), copied.getX());
        assertEquals(originalPosition.getY(), copied.getY());
    }

    @Test
    @DisplayName("create object next to coordinates")
    public void testGetPositionNextTo() {
        Position position = new Position(3,4);

        Position nextToPositionRight = position.getPositionNextTo(Direction.Right);
        assertEquals(nextToPositionRight.getX(), position.getX() + 1);
        assertEquals(position.getY(), nextToPositionRight.getY());

        Position nextToPositionLeft = position.getPositionNextTo(Direction.Left);
        assertEquals(nextToPositionLeft.getX(), position.getX() - 1);
        assertEquals(position.getY(), nextToPositionLeft.getY());

        Position nextToPositionDown = position.getPositionNextTo(Direction.Down);
        assertEquals(nextToPositionDown.getX(), position.getX());
        assertEquals(position.getY(), nextToPositionDown.getY() + 1);

        Position nextToPositionUp = position.getPositionNextTo(Direction.Up);
        assertEquals(nextToPositionUp.getX(), position.getX());
        assertEquals(position.getY(), nextToPositionUp.getY() - 1);
    }

    @Test
    @DisplayName("Is coords next to other coords")
    public void testIsNextTo() {
        Position position = new Position(3,4);

        /* Test position adjacent */
        assertTrue(position.isNextTo(new Position(2,4)));
        assertTrue(position.isNextTo(new Position(3,3)));
        assertTrue(position.isNextTo(new Position(3,5)));
        assertTrue(position.isNextTo(new Position(4,4)));

        /* Test close non-adjascent positions */
        assertFalse(position.isNextTo(new Position(2,3)));
        assertFalse(position.isNextTo(new Position(4,3)));
        assertFalse(position.isNextTo(new Position(2,5)));
        assertFalse(position.isNextTo(new Position(4,5)));

        /* Test far non-adjacent positions */
        assertFalse(position.isNextTo(new Position(3, 6)));
        assertFalse(position.isNextTo(new Position(1, 4)));
        assertFalse(position.isNextTo(new Position(5, 4)));
        assertFalse(position.isNextTo(new Position(3, 1)));

        /* Test that self is not adjascent */
        assertFalse(position.isNextTo(position));
    }

    @Test
    @DisplayName("coords equals to other")
    public void testEquals() {
        Board board = new Board(2, 5, 5, 2, 2);

        Position position1 = new Position(3,4);
        Position position2 = new Position(3,4);
        Position position3 = new Position(4,5);

        Pawn pawn1 = new Pawn( 'd', 2, 1, board);

        /* Test deux objets avec les memes coordonnées */
        assertTrue(position1.equals(position2));

        /* Test deux objets qui ont pas les memes coordonnées */
        assertFalse(position1.equals(position3));

        /* Test une instance position avec Pawn */
        assertFalse(position1.equals(pawn1));

    }
}