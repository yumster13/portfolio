/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g58412.chess.model.pieces;

import g58412.chess.model.Board;
import g58412.chess.model.Color;
import g58412.chess.model.Position;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author yumst
 */
public class FlyingBishopTest {
    
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    @Test
    public void testGetPossibleMovesBishopCornerLeftWHITE() {
        System.out.println("testGetPossibleMovesBishopCornerLeftWHITE");
        Position posB = new Position(0, 0);
        Piece pieceB = new FlyingBishop(Color.WHITE);
        Position posWP1 = new Position(2, 2);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(4, 4);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP1);
        board.setPiece(pieceWP2, posWP2);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 1),
                new Position(3, 3),
                new Position(4, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesBishopCornerLeftBLACK() {
        System.out.println("testGetPossibleMovesBishopCornerLeftBLACK");
        Position posB = new Position(0, 0);
        Piece pieceB = new FlyingBishop(Color.BLACK);
        Position posWP1 = new Position(2, 2);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(4, 4);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP1);
        board.setPiece(pieceWP2, posWP2);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 1),
                new Position(3, 3),
                new Position(4, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    
    @Test
    public void testGetPossibleMovesBishopCornerLeftWHITETOP() {
        System.out.println("testGetPossibleMovesBishopCornerLeftWHITETOP");
        Position posB = new Position(7, 0);
        Piece pieceB = new FlyingBishop(Color.WHITE);
        Position posWP1 = new Position(3, 4);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(0, 7);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP1);
        board.setPiece(pieceWP2, posWP2);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(6, 1),
                new Position(5, 2),
                new Position(4, 3),
                new Position(2, 5),
                new Position(1, 6),
                new Position(0, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    
    /*
     *      Permet de tester si deux listes de positions sont identiques à l'ordre
     *      des éléments prêts. Cette méthode est appelée
     *      par les méthodes de test.
     */
    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        System.out.println("assertEqualsIgnoringOrder");
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }
}
