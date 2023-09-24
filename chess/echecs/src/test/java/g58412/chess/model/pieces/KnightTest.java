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
public class KnightTest {
    
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    //-------------------------------------------------------------------------------------------
    //Tests for Knight
    @Test
    public void testGetPossibleMovesKnightMiddleWHITE() {
        System.out.println("testGetPossibleMovesKnightMiddleWHITE");
        Position posB = new Position(3, 3);
        Piece pieceB = new Knight(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(4, 1),
                new Position(5, 2),
                new Position(5, 4),
                new Position(4, 5),
                new Position(2, 5),
                new Position(1, 4),
                new Position(1, 2),
                new Position(2, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightMiddleBLACK() {
        System.out.println("testGetPossibleMovesKnightMiddleBLACK");
        Position posB = new Position(3, 3);
        Piece pieceB = new Knight(Color.BLACK);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(4, 1),
                new Position(5, 2),
                new Position(5, 4),
                new Position(4, 5),
                new Position(2, 5),
                new Position(1, 4),
                new Position(1, 2),
                new Position(2, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightMiddleWPawnsWHITE() {
        System.out.println("testGetPossibleMovesKnightMiddleWPawnsWHITE");
        Position posB = new Position(3, 3);
        Piece pieceB = new Knight(Color.WHITE);
        Position posWP1 = new Position(5, 4);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(1, 4);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        Position posBP = new Position(4, 5);
        Piece pieceBP = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceBP, posBP);

        List<Position> expected = List.of(
                new Position(4, 1),
                new Position(5, 2),
                new Position(4, 5),
                new Position(2, 5),
                new Position(1, 2),
                new Position(2, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightMiddleWPawnsBLACK() {
        System.out.println("testGetPossibleMovesKnightMiddleWPawnsBLACK");
        Position posB = new Position(3, 3);
        Piece pieceB = new Knight(Color.BLACK);
        Position posWP1 = new Position(5, 2);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(2, 5);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        Position posBP = new Position(1, 2);
        Piece pieceBP = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceBP, posBP);

        List<Position> expected = List.of(
                new Position(4, 1),
                new Position(5, 4),
                new Position(4, 5),
                new Position(1, 4),
                new Position(1, 2),
                new Position(2, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightCornerLeftWHITE() {
        System.out.println("testGetPossibleMovesKnightCornerLeftWHITE");
        Position posB = new Position(0, 0);
        Piece pieceB = new Knight(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(1, 2)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightCornerLeftBLACK() {
        System.out.println("testGetPossibleMovesKnightCornerLeftBLACK");
        Position posB = new Position(7, 0);
        Piece pieceB = new Knight(Color.BLACK);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(6, 2)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightCornerRightWHITE() {
        System.out.println("testGetPossibleMovesKnightCornerRightWHITE");
        Position posB = new Position(0, 7);
        Piece pieceB = new Knight(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(2, 6),
                new Position(1, 5)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKnightCornerRightBLACK() {
        System.out.println("testGetPossibleMovesKnightCornerRightBLACK");
        Position posB = new Position(7, 7);
        Piece pieceB = new Knight(Color.BLACK);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(5, 6),
                new Position(6, 5)
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
