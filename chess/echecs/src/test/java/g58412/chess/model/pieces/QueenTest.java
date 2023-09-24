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
public class QueenTest {
    
    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }
    //-------------------------------------------------------------------------------------------
    //Tests for the queen
    @Test
    public void testGetPossibleMovesQueenCornerLeftWHITE() {
        System.out.println("testGetPossibleMovesQueenCornerLeftWHITE");
        Position posB = new Position(0, 0);
        Piece pieceB = new Queen(Color.WHITE);
        Position posWP1 = new Position(1, 0);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(1, 1);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceB, posB);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 0),
                new Position(1, 1),
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(0, 5),
                new Position(0, 6),
                new Position(0, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleQueenQueenCornerRIGHTWHITE() {
        System.out.println("testGetPossibleQueenQueenCornerRIGHTWHITE");
        Position posB = new Position(0, 7);
        Piece pieceB = new Queen(Color.WHITE);
        Position posWP1 = new Position(1, 7);
        Piece pieceWP1 = new Pawn(Color.BLACK);
        Position posWP2 = new Position(1, 6);
        Piece pieceWP2 = new Pawn(Color.BLACK);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceB, posB);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(1, 7),
                new Position(1, 6),
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(0, 5),
                new Position(0, 6),
                new Position(0, 0)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleQueenQueenCornerRIGHTBlack() {
        System.out.println("testGetPossibleQueenQueenCornerRIGHTBlack");
        Position posB = new Position(7, 7);
        Piece pieceB = new Queen(Color.BLACK);
        Position posWP1 = new Position(6, 7);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(6, 6);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceB, posB);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(6, 7),
                new Position(6, 6),
                new Position(7, 1),
                new Position(7, 2),
                new Position(7, 3),
                new Position(7, 4),
                new Position(7, 5),
                new Position(7, 6),
                new Position(7, 0)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    @Test
    public void testGetPossibleMovesQueenCornerLeftBLACK() {
        System.out.println("testGetPossibleMovesQueenCornerLeftBLACK");
        Position posB = new Position(7, 0);
        Piece pieceB = new Queen(Color.BLACK);
        Position posWP1 = new Position(6, 0);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(6, 1);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        board.setPiece(pieceB, posB);
        board.setPiece(pieceWP1, posWP2);
        board.setPiece(pieceWP2, posWP1);
        board.setPiece(pieceB, posB);

        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(6, 0),
                new Position(6, 1),
                new Position(7, 1),
                new Position(7, 2),
                new Position(7, 3),
                new Position(7, 4),
                new Position(7, 5),
                new Position(7, 6),
                new Position(7, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
     @Test
    public void testGetPossibleMovesQueenMiddleBLACK() {
        System.out.println("testGetPossibleMovesQueenMiddleBLACK");
        Position posB = new Position(4, 4);
        Piece pieceB = new Queen(Color.BLACK);
        Position posWP1 = new Position(2, 2);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(6, 2);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        Position posBP = new Position(4, 1);
        Piece pieceBP = new Pawn(Color.BLACK);
        board.setPiece(pieceWP2, posWP2);
        board.setPiece(pieceWP1, posWP1);
        board.setPiece(pieceBP, posBP);
        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(5, 3),
                new Position(6, 2),
                new Position(5, 4),
                new Position(6, 4),
                new Position(7, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
                new Position(4, 3),
                new Position(4, 2),
                new Position(3, 5),
                new Position(2, 6),
                new Position(1, 7),
                new Position(3, 4),
                new Position(2, 4),
                new Position(1, 4),
                new Position(0, 4),
                new Position(3, 3),
                new Position(2, 2),
                new Position(4, 5),
                new Position(4, 6),
                new Position(4, 7)
        );
        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
     @Test
    public void testGetPossibleMovesQueenMiddleWHITE() {
        System.out.println("testGetPossibleMovesQueenMiddleWHITE");
        Position posB = new Position(4, 4);
        Piece pieceB = new Queen(Color.WHITE);
        Position posWP1 = new Position(2, 2);
        Piece pieceWP1 = new Pawn(Color.WHITE);
        Position posWP2 = new Position(6, 2);
        Piece pieceWP2 = new Pawn(Color.WHITE);
        Position posBP = new Position(4, 1);
        Piece pieceBP = new Pawn(Color.BLACK);
        board.setPiece(pieceWP2, posWP2);
        board.setPiece(pieceWP1, posWP1);
        board.setPiece(pieceBP, posBP);
        board.setPiece(pieceB, posB);

        List<Position> expected = List.of(
                new Position(5, 3),
                new Position(5, 4),
                new Position(6, 4),
                new Position(7, 4),
                new Position(5, 5),
                new Position(6, 6),
                new Position(7, 7),
                new Position(4, 3),
                new Position(4, 2),
                new Position(4, 1),
                new Position(3, 5),
                new Position(2, 6),
                new Position(1, 7),
                new Position(3, 4),
                new Position(2, 4),
                new Position(1, 4),
                new Position(0, 4),
                new Position(3, 3),
                new Position(4, 5),
                new Position(4, 6),
                new Position(4, 7)
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
