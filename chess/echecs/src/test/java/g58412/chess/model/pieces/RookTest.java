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
public class RookTest {
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    //-------------------------------------------------------------------------------------------
    //Test for the rook
    @Test
    public void testGetPossibleMovesRookCornerLeftBLACK() {
        System.out.println("testGetPossibleMovesRookCornerLeftBLACK");
        Position posB = new Position(7, 0);
        Piece pieceB = new Rook(Color.BLACK);
        Position posWP1 = new Position(3, 0);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(7,4);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(7, 1),
                new Position(7, 2),
                new Position(7, 3),
                new Position(7, 4),
                new Position(6, 0),
                new Position(5, 0),
                new Position(4, 0),
                new Position(3, 0)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleMovesRookCornerRightBLACK() {
        System.out.println("testGetPossibleMovesRookCornerRightBLACK");
        Position posB = new Position(7, 7);
        Piece pieceB = new Rook(Color.BLACK);
        Position posWP1 = new Position(4, 7);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(7,2);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(7, 2),
                new Position(7, 3),
                new Position(7, 4),
                new Position(7, 5),
                new Position(7, 6),
                new Position(4, 7),
                new Position(5, 7),
                new Position(6, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleMovesRookCornerRightWHITE() {
        System.out.println("testGetPossibleMovesRookCornerRightWHITE");
        Position posB = new Position(0, 7);
        Piece pieceB = new Rook(Color.WHITE);
        Position posWP1 = new Position(0, 5);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(7,7);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 7),
                new Position(2, 7),
                new Position(3, 7),
                new Position(4, 7),
                new Position(5, 7),
                new Position(6, 7),
                new Position(7, 7),
                new Position(0, 6),
                new Position(0, 5)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleMovesRookCornerLeftWHITE() {
        System.out.println("testGetPossibleMovesRookCornerLeftWHITE");
        Position posB = new Position(0, 0);
        Piece pieceB = new Rook(Color.WHITE);
        Position posWP1 = new Position(0, 4);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(4, 0);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(4, 0),
                new Position(3, 0),
                new Position(2, 0),
                new Position(1, 0)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
     @Test
    public void testGetPossibleMovesRookMiddle() {
        System.out.println("testGetPossibleMovesRookMiddle");
        Position posB = new Position(3, 3);
        Piece pieceB = new Rook(Color.WHITE);
        Position posWP1 = new Position(5, 3);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(3, 5);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(3, 5),
                new Position(3, 4),
                new Position(3, 2),
                new Position(3, 1),
                new Position(3, 0),
                new Position(2, 3),
                new Position(1, 3),
                new Position(0, 3),
                new Position(4, 3),
                new Position(5, 3)
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
