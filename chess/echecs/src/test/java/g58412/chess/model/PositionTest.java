/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g58412.chess.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author g58412
 */
public class PositionTest {
    
    @Test
    public void nextDirectionNW() {
        System.out.println("nextDirectionNW");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(4,2);
        Position result = pos1.next(Direction.NW);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    
    @Test
    public void nextDirectionN() {
        System.out.println("nextDirectionN");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(4,3);
        Position result = pos1.next(Direction.N);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionNE() {
        System.out.println("nextDirectionNE");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(4,4);
        Position result = pos1.next(Direction.NE);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionW() {
        System.out.println("nextDirectionW");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(3,2);
        Position result = pos1.next(Direction.W);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionE() {
        System.out.println("nextDirectionE");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(3,4);
        Position result = pos1.next(Direction.E);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionSW() {
        System.out.println("nextDirectionSW");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(2,2);
        Position result = pos1.next(Direction.SW);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionS() {
        System.out.println("nextDirectionS");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(2,3);
        Position result = pos1.next(Direction.S);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
    @Test
    public void nextDirectionSE() {
        System.out.println("nextDirectionSE");
        Position pos1 = new Position(3,3);
        Position pos2 = new Position(2,4);
        Position result = pos1.next(Direction.SE);
        assertEquals(result.getRow(), pos2.getRow());
        assertEquals(result.getColumn(), pos2.getColumn());
    }
}
