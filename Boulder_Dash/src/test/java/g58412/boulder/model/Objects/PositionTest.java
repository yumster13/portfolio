package g58412.boulder.model.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getX() {
        Position pos = new Position(3,3);
        assertEquals(3,pos.getX());
    }

    @Test
    void getY() {
        Position pos = new Position(3,3);
        assertEquals(3,pos.getY());
    }

    @Test
    void nextRIGHT() {
        Position pos = new Position(3, 3);
        assertEquals(new Position(4,3),pos.next(Direction.RIGHT));
    }
    @Test
    void nextLEFT() {
        Position pos = new Position(3, 3);
        assertEquals(new Position(2,3),pos.next(Direction.LEFT));
    }
    @Test
    void nextUP() {
        Position pos = new Position(3, 3);
        assertEquals(new Position(3,2),pos.next(Direction.UP));
    }
    @Test
    void nextDOWN() {
        Position pos = new Position(3, 3);
        assertEquals(new Position(3,4),pos.next(Direction.DOWN));
    }

    @Test
    void testEquals() {
        Position pos = new Position(4,4);
        assertTrue(new Position(4,4).equals(pos));
    }
}