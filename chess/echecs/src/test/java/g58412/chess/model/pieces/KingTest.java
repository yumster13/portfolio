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
public class KingTest {
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    
    //-------------------------------------------------------------------------------------------
    //Test for the King
    @Test
    public void testGetPossibleMovesKingMiddleBLACKWPawns() {
        System.out.println("testGetPossibleMovesKingMiddleBLACKWPawns");
        Position posB = new Position(3, 3);
        Piece pieceB = new King(Color.BLACK);
        Position posWP1 = new Position(4, 4);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(2, 3);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        Position posBP = new Position(4, 2);
        Piece pieceBP = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceBP, posBP);
        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(3, 2),
                new Position(4, 2),
                new Position(4, 3),
                new Position(3, 4),
                new Position(2, 4),
                new Position(2, 2)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingMiddleWHITEWPawns() {
        System.out.println("testGetPossibleMovesKingMiddleWHITEWPawns");
        Position posB = new Position(3, 3);
        Piece pieceB = new King(Color.WHITE);
        Position posWP1 = new Position(2, 2);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(3, 2);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        Position posBP = new Position(4, 4);
        Piece pieceBP = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceBP, posBP);
        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(2, 3),
                new Position(4, 2),
                new Position(4, 3),
                new Position(3, 4),
                new Position(2, 4),
                new Position(4, 4)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingMiddleWhite() {
        System.out.println("testGetPossibleMovesKingMiddleWhite");
        Position posB = new Position(3, 3);
        Piece pieceB = new King(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(3, 2),
                new Position(4, 2),
                new Position(4, 3),
                new Position(4, 4),
                new Position(3, 4),
                new Position(2, 4),
                new Position(2, 3),
                new Position(2, 2)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingMiddleWhiteWPawns() {
        System.out.println("testGetPossibleMovesKingMiddleWhiteWPawns");
        Position posB = new Position(3, 3);
        Piece pieceB = new King(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(3, 2),
                new Position(4, 2),
                new Position(4, 3),
                new Position(4, 4),
                new Position(3, 4),
                new Position(2, 4),
                new Position(2, 3),
                new Position(2, 2)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingCornerRightBLACK() {
        System.out.println("testGetPossibleMovesKnightCornerRightBLACK");
        Position posB = new Position(7, 7);
        Piece pieceB = new King(Color.BLACK);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(7, 6),
                new Position(6, 7),
                new Position(6, 6)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingCornerLeftBLACK() {
        System.out.println("testGetPossibleMovesKingCornerLeftBLACK");
        Position posB = new Position(7, 0);
        Piece pieceB = new King(Color.BLACK);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(6, 0),
                new Position(7, 1),
                new Position(6, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingCornerRightWHITE() {
        System.out.println("testGetPossibleMovesKingCornerRightWHITE");
        Position posB = new Position(0, 7);
        Piece pieceB = new King(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 7),
                new Position(0, 6),
                new Position(1, 6)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesKingCornerLeftWHITE() {
        System.out.println("testGetPossibleMovesKingCornerLeftWHITE");
        Position posB = new Position(0, 0);
        Piece pieceB = new King(Color.WHITE);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 0),
                new Position(0, 1),
                new Position(1, 1)
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
