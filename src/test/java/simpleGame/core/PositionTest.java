package simpleGame.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void getPositionNextTo() {
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