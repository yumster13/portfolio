/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package g58412.chess.model;

import g58412.chess.model.pieces.Pawn;
import g58412.chess.model.pieces.*;
import g58412.chess.view.TextView;
import g58412.chess.view.View;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yumst
 */
public class GameTest {

    @Test
    public void GetCurrentPlayerWHITE() {
        System.out.println("GetCurrentPlayer");
        Game game = new Game();
        game.start();
        Player player = new Player(Color.WHITE);
        assertEquals(player.getColor(), game.getCurrentPlayer().getColor());
    }

    @Test
    public void GetCurrentPlayerBLACK() {
        System.out.println("GetCurrentPlayer");
        Game game = new Game();
        game.start();
        Player player = new Player(Color.BLACK);
        assertEquals(player.getColor(), game.getCurrentPlayer().getColor().opposite());
    }

    @Test
    public void GetOppositePlayerWHITE() {
        System.out.println("GetOppositePlayerWHITE");
        Game game = new Game();
        game.start();
        Player player = new Player(Color.BLACK);
        assertEquals(player.getColor(), game.getOppositePlayer().getColor());
    }

    @Test
    public void GetOppositePlayerBLACK() {
        System.out.println("GetOppositePlayerBLACK");
        Game game = new Game();
        game.start();
        Player player = new Player(Color.WHITE);
        assertEquals(player.getColor(), game.getCurrentPlayer().getColor());
    }

    @Test
    public void testGetPiecepasOk() {
        System.out.println("testGetPiecepasOk");
        Game game = new Game();
        game.start();
        Position position = new Position(9, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            game.getPiece(position);
        });
    }

    @Test
    public void testPieceOk() {
        System.out.println("testPieceOk");
        Game game = new Game();
        game.start();
        Position position = new Position(7, 1);
        assertEquals(game.getPiece(position), game.getPiece(new Position(7, 1)));
    }

    @Test
    public void isCurrentPlayerPositionPasOK() {
        System.out.println("isCurrentPlayerPositionPasOK");
        Game game = new Game();
        game.start();
        Position position = new Position(9, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            game.isCurrentPlayerPosition(position);
        });
    }

    @Test
    public void isCurrentPlayerPositionOK() {
        System.out.println("isCurrentPlayerPositionOK");
        Game game = new Game();
        game.start();
        Position position = new Position(1, 1);
        assertTrue(game.isCurrentPlayerPosition(position));
    }

    @Test
    public void movePiecePositionWHITE() {
        System.out.println("movePiecePositionWHITE");
        Game game = new Game();
        game.start();
        Position oldPos = new Position(1, 1);
        Position newPos = new Position(2, 1);
        Piece piece = new Pawn(Color.WHITE);
        game.movePiecePosition(oldPos, newPos);
        assertEquals(piece.getColor(), game.getPiece(new Position(2, 1)).getColor());
    }

    @Test
    public void testGetPossibleMovesPBLACK() {
        System.out.println("testGetPossibleMovesPBLACK");
        Game game = new Game();
        game.start();

        List<Position> expected = List.of(
                new Position(5, 1),
                new Position(4, 1)
        );

        List<Position> positions = game.getPossibleMoves(new Position(6, 1));

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void testGetPossibleMovesPWHITE() {
        System.out.println("testGetPossibleMovesPWHITE");
        Game game = new Game();
        game.start();

        List<Position> expected = List.of(
                new Position(2, 1),
                new Position(3, 1)
        );

        List<Position> positions = game.getPossibleMoves(new Position(1, 1));

        assertEqualsIgnoringOrder(expected, positions);
    }

    @Test
    public void EchecEtMat(){
        System.out.println("echecEtMat");
        Game game = new Game();
        game.start();
        game.movePiecePosition(new Position(1,6), new Position(3,6));
        game.movePiecePosition(new Position(6,4), new Position(4,4));
        game.movePiecePosition(new Position(1,5), new Position(2,5));
        game.movePiecePosition(new Position(7,3), new Position(3,7));
        assertEquals(game.getState(), GameState.CHECK_MATE);
    }
    @Test
    public void MatduBerger(){
         System.out.println("MatduBerger");
        Game game = new Game();
        game.start();
        game.movePiecePosition(new Position(1,4), new Position(3,4));
        game.movePiecePosition(new Position(6,4), new Position(4,4));
        game.movePiecePosition(new Position(0,5), new Position(3,2));
        game.movePiecePosition(new Position(7,1), new Position(5,2));
        game.movePiecePosition(new Position(0,3), new Position(4,7));
        game.movePiecePosition(new Position(7,6), new Position(5,5));
        game.movePiecePosition(new Position(4,7), new Position(6,5));
        assertEquals(game.getState(), GameState.CHECK_MATE);
    }
    @Test
    public void Echec(){
        System.out.println("echec");
        Game game = new Game();
        game.start();
        Board board = game.getBoard();
        board.dropPiece(new Position(0,3));
        game.movePiecePosition(new Position(1,6), new Position(3,6));
        game.movePiecePosition(new Position(6,4), new Position(4,4));
        game.movePiecePosition(new Position(1,5), new Position(2,5));
        game.movePiecePosition(new Position(7,3), new Position(3,7));
        
        assertEquals(game.getState(), GameState.CHECK);
    }
    @Test
    public  void StaleMate(){
       System.out.println("StaleMate");
       Game game = new Game();
       game.start();
       Board board = game.getBoard();
        for (int i = 0; i < 8; i++) {
            board.dropPiece(new Position(0,i));
            board.dropPiece(new Position(1,i));
            board.dropPiece(new Position(6,i));
            board.dropPiece(new Position(7,i));
        }
        board.setPiece(new Pawn(Color.WHITE),new Position(2,3));
        board.setPiece(new Pawn(Color.BLACK),new Position(4,3));
        game.movePiecePosition(new Position(2,3),new Position(3,3));
        assertEquals(game.getState(), GameState.STALE_MATE);
    }
    
     @Test
     public void check(){
        System.out.println("echec");
        Game game = new Game();
        game.start();
        Board board = game.getBoard();
        View view = new TextView(game);
         dropPiece(0,game,board);
         dropPiece(1,game,board);
         dropPiece(6,game,board);
         dropPiece(7,game,board);
         board.setPiece(new King(Color.WHITE), new Position(0,0));
         board.setPiece(new King(Color.BLACK), new Position(7,0));
         board.setPiece(new Queen(Color.WHITE), new Position(4,1));
         board.setPiece(new Rook(Color.WHITE), new Position(0,7));
         board.setPiece(new Pawn(Color.BLACK), new Position(6,0));
         game.movePiecePosition(new Position(0,7), new Position(7,7));
         assertEquals(game.getState(), GameState.CHECK_MATE);
         
     }
     @Test
    public void test_State_Is_Stalemate1(){
         System.out.println("staleMate1");
        Game jeux = new Game();
        jeux.start();
        View view = new TextView(jeux);
        Board board = jeux.getBoard();
        jeux.movePiecePosition(new Position(1,4), new Position(2,4));//BLANC
        jeux.movePiecePosition(new Position(6,0), new Position(4,0));//NOIR
        jeux.movePiecePosition(new Position(0,3), new Position(4,7));//BLANC
        jeux.movePiecePosition(new Position(7,0), new Position(5,0));//NOIR
        jeux.movePiecePosition(new Position(4,7), new Position(4,0));//BLANC
        jeux.movePiecePosition(new Position(6,7), new Position(4,7));//NOIR
        jeux.movePiecePosition(new Position(1,7), new Position(3,7));//BLANC
        jeux.movePiecePosition(new Position(5,0), new Position(5,7));//NOIR
        jeux.movePiecePosition(new Position(4,0), new Position(6,2));//BLANC
        jeux.movePiecePosition(new Position(6,5), new Position(5,5));//NOIR
        jeux.movePiecePosition(new Position(6,2), new Position(6,3));//BLANC
        jeux.movePiecePosition(new Position(7,4), new Position(6,5));//NOIR
        jeux.movePiecePosition(new Position(6,3), new Position(6,1));//BLANC
        jeux.movePiecePosition(new Position(7,3), new Position(2,3));//NOIR
        jeux.movePiecePosition(new Position(6,1), new Position(7,1));//BLANC
        jeux.movePiecePosition(new Position(2,3), new Position(6,7));//NOIR
        jeux.movePiecePosition(new Position(7,1), new Position(7,2));//BLANC
        jeux.movePiecePosition(new Position(6,5), new Position(5,6));//NOIR
        jeux.movePiecePosition(new Position(7,2), new Position(5,4));//BLANC
        assertEquals(jeux.getState(), GameState.STALE_MATE);          
    }
     public void dropPiece(int j,Game game,Board board){
         for (int i = 0; i < 8; i++) {
            board.dropPiece(new Position(j,i));
        }
     }
    @Test
    public void testNewQuennWhite(){
       System.out.println("testNewQuennWhite");
        Game game = new Game();
        game.start();
        Board board = game.getBoard(); 
        dropPiece(0,game,board);
         dropPiece(1,game,board);
         dropPiece(6,game,board);
         dropPiece(7,game,board);
         board.setPiece(new King(Color.WHITE), new Position(5,4));
          board.setPiece(new King(Color.BLACK), new Position(1,0));
         board.setPiece(new Pawn(Color.WHITE), new Position(6,1));
        game.movePiecePosition(new Position(6,1), new Position(7,1));
        assertEquals(board.getPiece(new Position(7,1)), new Queen(Color.WHITE));
    }
    @Test
    public void testNotQuennWhite(){
       System.out.println("testNotQuennWhite");
        Game game = new Game();
        game.start();
        Board board = game.getBoard(); 
        dropPiece(0,game,board);
         dropPiece(1,game,board);
         dropPiece(6,game,board);
         dropPiece(7,game,board);
         board.setPiece(new King(Color.WHITE), new Position(5,4));
          board.setPiece(new King(Color.BLACK), new Position(1,0));
         board.setPiece(new Pawn(Color.WHITE), new Position(5,1));
        game.movePiecePosition(new Position(5,1), new Position(6,1));
        assertEquals(board.getPiece(new Position(6,1)), new Pawn(Color.WHITE));
    }
    @Test
    public void testNewQuennBlack(){
       System.out.println("testNewQuennBlack");
        Game game = new Game();
        game.start();
        game.setCurrentPlayer(new Player(Color.BLACK));
        Board board = game.getBoard(); 
        dropPiece(0,game,board);
         dropPiece(1,game,board);
         dropPiece(6,game,board);
         dropPiece(7,game,board);
         board.setPiece(new King(Color.WHITE), new Position(5,4));
          board.setPiece(new King(Color.BLACK), new Position(1,0));
         board.setPiece(new Pawn(Color.BLACK), new Position(1,1));
        game.movePiecePosition(new Position(1,1), new Position(0,1));
        assertEquals(board.getPiece(new Position(0,1)), new Queen(Color.BLACK));
    }
    @Test
    public void testNotQuennBlack(){
       System.out.println("testNotQuennBlack");
        Game game = new Game();
        game.start();
         game.setCurrentPlayer(new Player(Color.BLACK));
        Board board = game.getBoard(); 
        dropPiece(0,game,board);
         dropPiece(1,game,board);
         dropPiece(6,game,board);
         dropPiece(7,game,board);
         board.setPiece(new King(Color.WHITE), new Position(7,0));
          board.setPiece(new King(Color.BLACK), new Position(4,3));
         board.setPiece(new Pawn(Color.BLACK), new Position(2,1));
        game.movePiecePosition(new Position(2,1), new Position(1,1));
        assertEquals(board.getPiece(new Position(1,1)), new Pawn(Color.BLACK));
    }
    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        System.out.println("assertEqualsIgnoringOrder");
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

}
