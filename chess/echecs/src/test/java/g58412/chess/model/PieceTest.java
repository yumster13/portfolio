package g58412.chess.model;

import g58412.chess.model.pieces.*;
import g58412.chess.model.pieces.Piece;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author srexhep
 */
public class PieceTest {

    Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testGetPossibleMovesPWHITE() {
        System.out.println("testGetPossibleMovesPWHITE");
        Position position = new Position(1, 1);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesPBLACK() {
        System.out.println("testGetPossibleMovesPBLACK");
        Position position = new Position(6, 1);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesUneCaseBLACK() {
        System.out.println("testGetPossibleMovesUneCaseBLACK");
        Position position = new Position(5, 1);
        Piece piece = new Pawn(Color.BLACK);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(4, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesUneCaseWHITE() {
        System.out.println("testGetPossibleMovesUneCaseWHITE");
        Position position = new Position(2, 1);
        Piece piece = new Pawn(Color.WHITE);
        board.setPiece(piece, position);

        List<Position> expected = List.of(
                new Position(3, 1)
        );

        List<Position> positions = piece.getPossibleMoves(position, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesDiagonaleSW() {
        System.out.println("testGetPossibleMovesDiagonaleSW");
        Position posB = new Position(6, 1);
        Piece pieceB = new Pawn(Color.BLACK);

        Position posW = new Position(5, 0);
        Piece pieceW = new Pawn(Color.WHITE);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(5, 0),
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesDiagonaleSE() {
        System.out.println("testGetPossibleMovesDiagonaleSE");
        Position posB = new Position(6, 1);
        Piece pieceB = new Pawn(Color.BLACK);

        Position posW = new Position(5, 2);
        Piece pieceW = new Pawn(Color.WHITE);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(5, 2),
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesDiagonaleNW() {
        System.out.println("testGetPossibleMovesDiagonaleNW");
        Position posB = new Position(1, 1);
        Piece pieceB = new Pawn(Color.WHITE);

        Position posW = new Position(2, 0);
        Piece pieceW = new Pawn(Color.BLACK);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(2, 0),
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesDiagonaleNE() {
        System.out.println("testGetPossibleMovesDiagonaleNE");
        Position posB = new Position(1, 1);
        Piece pieceB = new Pawn(Color.WHITE);

        Position posW = new Position(2, 2);
        Piece pieceW = new Pawn(Color.BLACK);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(2, 2),
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesNotDiagonaleNE() {
        System.out.println("testGetPossibleMovesNotDiagonaleNE");
        Position posB = new Position(1, 1);
        Piece pieceB = new Pawn(Color.WHITE);

        Position posW = new Position(2, 2);
        Piece pieceW = new Pawn(Color.WHITE);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesNotDiagonaleNW() {
        System.out.println("testGetPossibleMovesNotDiagonaleNW");
        Position posB = new Position(1, 1);
        Piece pieceB = new Pawn(Color.WHITE);

        Position posW = new Position(2, 0);
        Piece pieceW = new Pawn(Color.WHITE);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(3, 1),
                new Position(2, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesNotDiagonaleSW() {
        System.out.println("testGetPossibleMovesNotDiagonaleSW");
        Position posB = new Position(6, 1);
        Piece pieceB = new Pawn(Color.BLACK);

        Position posW = new Position(5, 0);
        Piece pieceW = new Pawn(Color.BLACK);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesNotDiagonaleSE() {
        System.out.println("testGetPossibleMovesNotDiagonaleSE");
        Position posB = new Position(6, 1);
        Piece pieceB = new Pawn(Color.BLACK);

        Position posW = new Position(5, 2);
        Piece pieceW = new Pawn(Color.BLACK);

        board.setPiece(pieceB, posB);
        board.setPiece(pieceW, posW);

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = pieceB.getPossibleMoves(posB, board);

        assertEqualsIgnoringOrder(expected, positions);
    }
    
    /**
     * ******* A vous d'écrire les autres cas. Veuillez faire attention à avoir
     * un plan de test complet (comme précisé dans l'énoncé) **********
     */

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
