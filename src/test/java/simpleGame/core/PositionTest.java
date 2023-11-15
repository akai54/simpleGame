package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static simpleGame.core.Direction.Right;

class PositionTest {

    @Test
    @DisplayName("Constructor correctly initialized")
    void testPositionConstructor() {
        Position position = new Position(3,4);
        assertEquals(3, position.getX());
        assertEquals(4, position.getY());
    }

    @Test
    @DisplayName("create object with same coordinates")
    void testCopy() {
        Position originalPosition = new Position(3,4);
        Position copied = originalPosition.copy();

        assertEquals(originalPosition.getX(), copied.getX());
        assertEquals(originalPosition.getY(), copied.getY());
    }

    @Test
    @DisplayName("create object next to coordinates")
    void testGetPositionNextTo() {
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
    void isNextTo() {
    }

    @Test
    @DisplayName("coords equals to other")
    void testEquals() {
    }
}